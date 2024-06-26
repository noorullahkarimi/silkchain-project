package com.silkchain.Silkchain.dao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WalletAuthenticationFilter extends BasicAuthenticationFilter {

    private static Cache<String, Boolean> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .build();

    public WalletAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String walletAddress = request.getHeader("Wallet-Address");

        if (walletAddress != null && isAuthorized(walletAddress)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(walletAddress, null, List.of(() -> "ROLE_ADMIN"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private boolean isAuthorized(String walletAddress) throws IOException {
        Boolean isAuthorized = cache.getIfPresent(walletAddress);
        if (isAuthorized == null) {
            List<Member> members = fetchMembersFromApi();
            isAuthorized = members.stream().anyMatch(member -> member.id.contains(walletAddress));
            cache.put(walletAddress, isAuthorized);
        }
        return isAuthorized;
    }

    private List<Member> fetchMembersFromApi() throws IOException {
        String jsonResponse = "{ \"data\": { \"members\": [{ \"createdAt\": \"1717271808\", \"id\": \"0x4dcc3bc33af8ab00f15664a234d90a18258f33af-member-0x49743d3eb9c3deb7a5bdb7b4a41a22ad4a4e45dd\" }, { \"createdAt\": \"1717271760\", \"id\": \"0x4dcc3bc33af8ab00f15664a234d90a18258f33af-member-0x568adb025ac998f7df8978cf501cbd760613c338\" }, { \"createdAt\": \"1717855056\", \"id\": \"0x4dcc3bc33af8ab00f15664a234d90a18258f33af-member-0x7026faefb81299d37d13ecee334b9c31ca2bef6d\" }, { \"createdAt\": \"1717093332\", \"id\": \"0x4dcc3bc33af8ab00f15664a234d90a18258f33af-member-0x908dd4739495d5a4f88515dcd35bfccc3622ee3f\" }, { \"createdAt\": \"1716971748\", \"id\": \"0x4dcc3bc33af8ab00f15664a234d90a18258f33af-member-0x931a93b13a598c6b3cc9e051239dec721701b78د\", { \"createdAt\": \"1716971748\", \"id\": \"0x4dcc3bc33af8ab00f15664a234d90a18258f33af-member-0xbce17dd03a5a0628dc73979ec4d392911069a2f7\" }, { \"createdAt\": \"1716971748\", \"id\": \"0x4dcc3bc33af8ab00f15664a234d90a18258f33af-member-0xda84d0733558295536b1d1f76553ad9787716e5b\" }] } }";

        ObjectMapper objectMapper = new ObjectMapper();
        ApiResponse apiResponse = objectMapper.readValue(jsonResponse, ApiResponse.class);
        return apiResponse.data.members;
    }

    // مدل برای داده‌های JSON
    public static class Member {
        public String createdAt;
        public String id;
    }

    public static class Data {
        public List<Member> members;
    }

    public static class ApiResponse {
        public Data data;
    }
}