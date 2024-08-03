package com.silkchain.Silkchain.service;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.*;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.*;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.request.EthFilter;

import java.math.BigInteger;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.EthLog.LogResult;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import org.web3j.abi.datatypes.Event;
//import org.web3j.abi.datatypes.TypeReference;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Bytes32;
//import org.web3j.abi.datatypes.generated.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.EthLog.LogResult;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Async;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;
import org.web3j.utils.Convert;

import java.util.List;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.web3j.protocol.core.methods.response.EthLog;

import com.google.gson.JsonObject;

import javax.xml.bind.DatatypeConverter;
import java.util.concurrent.CompletableFuture;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.EventEncoder;
import org.web3j.utils.Numeric;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.util.Arrays;

public class TestEventListener {
    //    private static final String INFURA_URL = "https://sepolia.infura.io/v3/64dfbe5332794e20b8001d3c5f271956";
//    private static final String CONTRACT_ADDRESS = "0x4DCc3bc33aF8ab00f15664a234d90a18258f33Af";
    private static final String INFURA_URL = "https://sepolia.infura.io/v3/64dfbe5332794e20b8001d3c5f271956";
    private static final String CONTRACT_ADDRESS = "0x4DCc3bc33aF8ab00f15664a234d90a18258f33Af";
    private static final String SUBMIT_PROPOSAL_EVENT_SIGNATURE = "0x" + Numeric.toHexStringNoPrefix(
            Hash.sha3("SubmitProposal (index_topic_1 uint256 proposal, index_topic_2 bytes32 proposalDataHash, uint256 votingPeriod, bytes proposalData, uint256 expiration, uint256 baalGas, bool selfSponsor, uint256 timestamp, string details)".getBytes())
    );
    private static final String EVENT_SIGNATURE = "0xb9956173924f9c1204bae41dd3737d7ed1161846d13183879cdc03c4b9f8d019";
    private static final String CONTRACT_ABI = "[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"guard_\",\"type\":\"address\"}],\"name\":\"NotIERC165Compliant\",\"type\":\"error\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"owner\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"spender\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"Approval\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"previousAvatar\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"newAvatar\",\"type\":\"address\"}],\"name\":\"AvatarSet\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"proposal\",\"type\":\"uint256\"}],\"name\":\"CancelProposal\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"address\",\"name\":\"guard\",\"type\":\"address\"}],\"name\":\"ChangedGuard\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"uint32\",\"name\":\"voting\",\"type\":\"uint32\"},{\"indexed\":false,\"internalType\":\"uint32\",\"name\":\"grace\",\"type\":\"uint32\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"newOffering\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"quorum\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"sponsor\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"minRetention\",\"type\":\"uint256\"}],\"name\":\"GovernanceConfigSet\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"uint8\",\"name\":\"version\",\"type\":\"uint8\"}],\"name\":\"Initialized\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"adminLock\",\"type\":\"bool\"}],\"name\":\"LockAdmin\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"governorLock\",\"type\":\"bool\"}],\"name\":\"LockGovernor\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"managerLock\",\"type\":\"bool\"}],\"name\":\"LockManager\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"paused\",\"type\":\"bool\"}],\"name\":\"LootPaused\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"previousOwner\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"newOwner\",\"type\":\"address\"}],\"name\":\"OwnershipTransferred\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"proposal\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"passed\",\"type\":\"bool\"},{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"actionFailed\",\"type\":\"bool\"}],\"name\":\"ProcessProposal\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"member\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"lootToBurn\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"sharesToBurn\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"address[]\",\"name\":\"tokens\",\"type\":\"address[]\"}],\"name\":\"Ragequit\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"forwarder\",\"type\":\"address\"}],\"name\":\"SetTrustedForwarder\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"lootPaused\",\"type\":\"bool\"},{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"sharesPaused\",\"type\":\"bool\"},{\"indexed\":false,\"internalType\":\"uint32\",\"name\":\"gracePeriod\",\"type\":\"uint32\"},{\"indexed\":false,\"internalType\":\"uint32\",\"name\":\"votingPeriod\",\"type\":\"uint32\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"proposalOffering\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"quorumPercent\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"sponsorThreshold\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"minRetentionPercent\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"name\",\"type\":\"string\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"symbol\",\"type\":\"string\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"totalShares\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"totalLoot\",\"type\":\"uint256\"}],\"name\":\"SetupComplete\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"shaman\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"permission\",\"type\":\"uint256\"}],\"name\":\"ShamanSet\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"paused\",\"type\":\"bool\"}],\"name\":\"SharesPaused\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"member\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"proposal\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"votingStarts\",\"type\":\"uint256\"}],\"name\":\"SponsorProposal\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"proposal\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"proposalDataHash\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"votingPeriod\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"bytes\",\"name\":\"proposalData\",\"type\":\"bytes\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"expiration\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"baalGas\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"selfSponsor\",\"type\":\"bool\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"timestamp\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"details\",\"type\":\"string\"}],\"name\":\"SubmitProposal\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"member\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"balance\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"proposal\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"bool\",\"name\":\"approved\",\"type\":\"bool\"}],\"name\":\"SubmitVote\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"previousTarget\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"newTarget\",\"type\":\"address\"}],\"name\":\"TargetSet\",\"type\":\"event\"},{\"inputs\":[],\"name\":\"adminLock\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"avatar\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address[]\",\"name\":\"from\",\"type\":\"address[]\"},{\"internalType\":\"uint256[]\",\"name\":\"amount\",\"type\":\"uint256[]\"}],\"name\":\"burnLoot\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address[]\",\"name\":\"from\",\"type\":\"address[]\"},{\"internalType\":\"uint256[]\",\"name\":\"amount\",\"type\":\"uint256[]\"}],\"name\":\"burnShares\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint32\",\"name\":\"id\",\"type\":\"uint32\"}],\"name\":\"cancelProposal\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes[]\",\"name\":\"_calls\",\"type\":\"bytes[]\"},{\"internalType\":\"address\",\"name\":\"_target\",\"type\":\"address\"}],\"name\":\"encodeMultisend\",\"outputs\":[{\"internalType\":\"bytes\",\"name\":\"encodedMultisend\",\"type\":\"bytes\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"_value\",\"type\":\"uint256\"},{\"internalType\":\"bytes\",\"name\":\"_data\",\"type\":\"bytes\"}],\"name\":\"executeAsBaal\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getGuard\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"_guard\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint32\",\"name\":\"id\",\"type\":\"uint32\"}],\"name\":\"getProposalStatus\",\"outputs\":[{\"internalType\":\"bool[4]\",\"name\":\"\",\"type\":\"bool[4]\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"governorLock\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"gracePeriod\",\"outputs\":[{\"internalType\":\"uint32\",\"name\":\"\",\"type\":\"uint32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"guard\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes\",\"name\":\"_transactions\",\"type\":\"bytes\"}],\"name\":\"hashOperation\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"hash\",\"type\":\"bytes32\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"shaman\",\"type\":\"address\"}],\"name\":\"isAdmin\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"shaman\",\"type\":\"address\"}],\"name\":\"isGovernor\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"shaman\",\"type\":\"address\"}],\"name\":\"isManager\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"forwarder\",\"type\":\"address\"}],\"name\":\"isTrustedForwarder\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"latestSponsoredProposalId\",\"outputs\":[{\"internalType\":\"uint32\",\"name\":\"\",\"type\":\"uint32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"lockAdmin\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"lockGovernor\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"lockManager\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"lootToken\",\"outputs\":[{\"internalType\":\"contract IBaalToken\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"managerLock\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"},{\"internalType\":\"uint32\",\"name\":\"\",\"type\":\"uint32\"}],\"name\":\"memberVoted\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"minRetentionPercent\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address[]\",\"name\":\"to\",\"type\":\"address[]\"},{\"internalType\":\"uint256[]\",\"name\":\"amount\",\"type\":\"uint256[]\"}],\"name\":\"mintLoot\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address[]\",\"name\":\"to\",\"type\":\"address[]\"},{\"internalType\":\"uint256[]\",\"name\":\"amount\",\"type\":\"uint256[]\"}],\"name\":\"mintShares\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"multisendLibrary\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"owner\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint32\",\"name\":\"id\",\"type\":\"uint32\"},{\"internalType\":\"bytes\",\"name\":\"proposalData\",\"type\":\"bytes\"}],\"name\":\"processProposal\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"proposalCount\",\"outputs\":[{\"internalType\":\"uint32\",\"name\":\"\",\"type\":\"uint32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"proposalOffering\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"proposals\",\"outputs\":[{\"internalType\":\"uint32\",\"name\":\"id\",\"type\":\"uint32\"},{\"internalType\":\"uint32\",\"name\":\"prevProposalId\",\"type\":\"uint32\"},{\"internalType\":\"uint32\",\"name\":\"votingStarts\",\"type\":\"uint32\"},{\"internalType\":\"uint32\",\"name\":\"votingEnds\",\"type\":\"uint32\"},{\"internalType\":\"uint32\",\"name\":\"graceEnds\",\"type\":\"uint32\"},{\"internalType\":\"uint32\",\"name\":\"expiration\",\"type\":\"uint32\"},{\"internalType\":\"uint256\",\"name\":\"baalGas\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"yesVotes\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"noVotes\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"maxTotalSharesAndLootAtVote\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"maxTotalSharesAtSponsor\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"sponsor\",\"type\":\"address\"},{\"internalType\":\"bytes32\",\"name\":\"proposalDataHash\",\"type\":\"bytes32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"quorumPercent\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"sharesToBurn\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"lootToBurn\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"tokens\",\"type\":\"address[]\"}],\"name\":\"ragequit\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"renounceOwnership\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bool\",\"name\":\"pauseShares\",\"type\":\"bool\"},{\"internalType\":\"bool\",\"name\":\"pauseLoot\",\"type\":\"bool\"}],\"name\":\"setAdminConfig\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_avatar\",\"type\":\"address\"}],\"name\":\"setAvatar\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes\",\"name\":\"_governanceConfig\",\"type\":\"bytes\"}],\"name\":\"setGovernanceConfig\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_guard\",\"type\":\"address\"}],\"name\":\"setGuard\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address[]\",\"name\":\"_shamans\",\"type\":\"address[]\"},{\"internalType\":\"uint256[]\",\"name\":\"_permissions\",\"type\":\"uint256[]\"}],\"name\":\"setShamans\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_target\",\"type\":\"address\"}],\"name\":\"setTarget\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_trustedForwarderAddress\",\"type\":\"address\"}],\"name\":\"setTrustedForwarder\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes\",\"name\":\"_initializationParams\",\"type\":\"bytes\"}],\"name\":\"setUp\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"shamans\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"sharesToken\",\"outputs\":[{\"internalType\":\"contract IBaalToken\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint32\",\"name\":\"id\",\"type\":\"uint32\"}],\"name\":\"sponsorProposal\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"sponsorThreshold\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint32\",\"name\":\"id\",\"type\":\"uint32\"}],\"name\":\"state\",\"outputs\":[{\"internalType\":\"enum Baal.ProposalState\",\"name\":\"\",\"type\":\"uint8\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes\",\"name\":\"proposalData\",\"type\":\"bytes\"},{\"internalType\":\"uint32\",\"name\":\"expiration\",\"type\":\"uint32\"},{\"internalType\":\"uint256\",\"name\":\"baalGas\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"details\",\"type\":\"string\"}],\"name\":\"submitProposal\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint32\",\"name\":\"id\",\"type\":\"uint32\"},{\"internalType\":\"bool\",\"name\":\"approved\",\"type\":\"bool\"}],\"name\":\"submitVote\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"voter\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"expiry\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"nonce\",\"type\":\"uint256\"},{\"internalType\":\"uint32\",\"name\":\"id\",\"type\":\"uint32\"},{\"internalType\":\"bool\",\"name\":\"approved\",\"type\":\"bool\"},{\"internalType\":\"uint8\",\"name\":\"v\",\"type\":\"uint8\"},{\"internalType\":\"bytes32\",\"name\":\"r\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"s\",\"type\":\"bytes32\"}],\"name\":\"submitVoteWithSig\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"target\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"totalLoot\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"totalShares\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"totalSupply\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"newOwner\",\"type\":\"address\"}],\"name\":\"transferOwnership\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"trustedForwarder\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"versionRecipient\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"votingNonces\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"votingPeriod\",\"outputs\":[{\"internalType\":\"uint32\",\"name\":\"\",\"type\":\"uint32\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]";

    //        private static final String CONTRACT_ADDRESS = "0x4DCc3bc33aF8ab00f15664a234d90a18258f33Af";
//        private static final String INFURA_URL = "https://sepolia.infura.io/v3/64dfbe5332794e20b8001d3c5f271956";
    public static void main(String[] args) throws Exception {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(60, TimeUnit.SECONDS); // افزایش زمان تایم‌اوت اتصال
//        builder.readTimeout(60, TimeUnit.SECONDS); // افزایش زمان تایم‌اوت خواندن
//        HttpService httpService = new HttpService("https://sepolia.infura.io/v3/64dfbe5332794e20b8001d3c5f271956", builder.build());

//        Web3j web3j = Web3j.build(httpService);
        Web3j web3j = Web3j.build(new HttpService(INFURA_URL));
//        EthLog ethLog = web3j.ethGetLogs(new EthFilter(
//                DefaultBlockParameterName.EARLIEST,
//                DefaultBlockParameterName.LATEST,
//                CONTRACT_ADDRESS
//        )).send();
        // ایجاد فیلتر برای دریافت رویدادهای قرارداد
        EthFilter filter = new EthFilter(
                DefaultBlockParameter.valueOf(new BigInteger("0")), DefaultBlockParameterName.LATEST, CONTRACT_ADDRESS
        );
//        EthFilter filter = new EthFilter(
//                DefaultBlockParameterName.EARLIEST,
//                DefaultBlockParameterName.LATEST,
//                CONTRACT_ADDRESS
//        );
//        CompletableFuture<EthLog> logFuture = web3j.ethGetLogs(filter).sendAsync();
//
//        logFuture.thenAccept(logs -> {
//                    List<EthLog.LogResult> logResults = logs.getLogs();
//                    for (EthLog.LogResult logResult : logResults) {
//                        Log log = (Log) logResult.get();
//                        System.out.println("New event line 139: " + log);
//                        String eventData = log.getData();
//                        System.out.println("Raw Event Data: " + eventData);
//                        String extractedString = extractString(eventData, 1606, 40);
//                        System.out.println("Extracted String: " + extractedString);
//
//                        String description = extractDescription(eventData);
//                        System.out.println("Description: " + description);
//                    }
//                });
//        Event submitProposalEvent = new Event("SubmitProposal",
//                Arrays.<TypeReference<?>>asList(
//                        new TypeReference<Uint256>() {
//                        }, // اگر نوع دیگری دارید، آن را جایگزین کنید
//                        new TypeReference<Uint256>() {
//                        }  // اگر نوع دیگری دارید، آن را جایگزین کنید
//                )
//        );

//        String encodedEventSignature = EventEncoder.encode(submitProposalEvent);
//        filter.addSingleTopic(encodedEventSignature);
//        System.out.println("we came to here>>" + web3j.ethLogFlowable(filter));
        web3j.ethLogFlowable(filter).subscribe(log -> {
//            System.out.println("New event: " + log);
            String targetTopic = "0xb9956173924f9c1204bae41dd3737d7ed1161846d13183879cdc03c4b9f8d019";
            boolean found = false;

            for (String topic : log.getTopics()) {
                if (topic.equals(targetTopic)) {
                    if (log.getData().length()> 1858) {
                        found = true;
                        break;
                    }
                }
            }

            if (found) {
                System.out.println("data length equals --------------------->> " + log.getData().length());
                String eventData = log.getData();
                System.out.println("Event data--------------------->>" + eventData);
                String extractedString = extractString(eventData, 1606, 40);
                System.out.println("Extracted String: 0x" + extractedString);

                String description = extractDescription(eventData);
                System.out.println("Description: " + description);
            }

//            String extractedString = extractString(eventData, 1606, 40);
//            System.out.println("Extracted String: " + extractedString);
//
//            String description = extractDescription(eventData);
//            System.out.println("Description: " + description);
//            if (eventData.length() == ) {
                // استخراج داده‌های خاص از eventData (برای مثال)
//                String extractedString = extractString(eventData, 1606, 40);
//                System.out.println("Extracted String: " + extractedString);
//
//                String description = extractDescription(eventData);
//                System.out.println("Description: " + description);
//            }else {
//                System.out.println("------------------Short data---------------------");
//            }
        });

        // نگه‌داشتن برنامه در حال اجرا
        Thread.sleep(Long.MAX_VALUE);
    }

    public static String extractString(String data, int start, int length) {
        return data.substring(start, start + length);
    }

    public static String hexToString(String hex) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }

    public static String extractDescription(String data) {
        try {
            int jsonStartHex = data.indexOf("7b"); // '{' in hex
            int jsonEndHex = data.indexOf("7d", jsonStartHex) + 2; // '}' in hex

            if (jsonStartHex != -1 && jsonEndHex != -1) {
                String jsonHex = data.substring(jsonStartHex, jsonEndHex);
                String jsonString = hexToString(jsonHex);
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    return jsonObject.optString("description", "Description not found");
                } catch (JSONException e) {
                    return "Error parsing JSON";
                }
            } else {
                return "JSON part not found";
            }
        } catch (StringIndexOutOfBoundsException e) {
            return "Error processing string data";
        }
    }

}









//        Gson gson = new Gson();
//        String x = "{removed=false, logIndex='0x3a', transactionIndex='0x1b', transactionHash='0x81ba8b24e0be79ed0af11370aca25f782bdef17ecb1fea28d6c98bcea6a353ce', blockHash='0x04066fd7eb0383d328d89ce1032514f12b247ca046dcade00b53c9d526fa427e', blockNumber='0x5b8c83', address='0x4dcc3bc33af8ab00f15664a234d90a18258f33af', data='0x000000000000000000000000000000000000000000000000000000000000038400000000000000000000000000000000000000000000000000000000000000e00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006aca60000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000006657036c00000000000000000000000000000000000000000000000000000000000003a000000000000000000000000000000000000000000000000000000000000002848d80ff0a00000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000232004dcc3bc33af8ab00f15664a234d90a18258f33af000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000c4b1e3f40c000000000000000000000000000000000000000000000000000000000000004000000000000000000000000000000000000000000000000000000000000000800000000000000000000000000000000000000000000000000000000000000001000000000000000000000000908dd4739495d5a4f88515dcd35bfccc3622ee3f0000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000001158e460913d00000004dcc3bc33af8ab00f15664a234d90a18258f33af000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000c44526d846000000000000000000000000000000000000000000000000000000000000004000000000000000000000000000000000000000000000000000000000000000800000000000000000000000000000000000000000000000000000000000000001000000000000000000000000908dd4739495d5a4f88515dcd35bfccc3622ee3f0000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000001158e460913d0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000737b227469746c65223a22616464206d656d626572222c226465736372697074696f6e223a22616464206d722072617a61766920222c22636f6e74656e74555249223a22222c22636f6e74656e7455524954797065223a2275726c222c2270726f706f73616c54797065223a224953535545227d00000000000000000000000000', type='null', topics=[0xb9956173924f9c1204bae41dd3737d7ed1161846d13183879cdc03c4b9f8d019, 0x0000000000000000000000000000000000000000000000000000000000000001, 0x57d361b4fee0160a0eaef6de042ac324ec8de7e863d615bd8b7811536ac9d126]}";
//        JsonObject jsonObject = gson.fromJson(x, JsonObject.class);
//
//        // استخراج داده‌ها
//        String dataHex = jsonObject.get("data").getAsString();
//        System.out.println("data--->>> " + dataHex);


//        Web3j web3j = Web3j.build(new HttpService(INFURA_URL));
//
//        // Create the filter for the event
//        EthLog ethLog = web3j.ethGetLogs(new org.web3j.protocol.core.methods.request.EthFilter(
//                DefaultBlockParameterName.EARLIEST,
//                DefaultBlockParameterName.LATEST,
//                CONTRACT_ADDRESS
//        )).send();
//        String resultToString = convertToJson(ethLog);
//        System.out.println("type of--->>> " +resultToString);
//        System.out.println("type of--->>> " +  resultToString.getClass());
//        JsonParser jsonParser = new JsonParser();
//        var resultToJson = jsonParser.parse(resultToString);
//        JsonArray results = resultToJson.getAsJsonArray();
//
//        for (JsonElement result : results) {
//            JsonObject resultObject = result.getAsJsonObject();
//            String dataHex = resultObject.get("data").getAsString();
//
//            // تبدیل داده هگزادسیمال به نوع داده مناسب (مثلاً با استفاده از کتابخانه Web3j)
//            byte[] dataBytes = DatatypeConverter.parseHexBinary(dataHex);
//            // ... (استفاده از داده های تبدیل شده)
//            System.out.println("this is byte code--->>> " +  dataBytes);
//        }
        // Process the logs
//        List<EthLog.LogResult> logResults = ethLog.getLogs();
//        for (EthLog.LogResult logResult : logResults) {
//
//            Gson gson = new Gson();
//            gson.


//            if (isValidJson(resultToString)) { // Check if the string is a valid JSON using a helper method
//                try {
//
//                    String dataHex = resultToJson.get("data").getAsString();
//                    System.out.println("data successfull" );
//                } catch (JsonSyntaxException e) {
//                    System.err.println("Error parsing data: " + e.getMessage());
//                }
//            } else {
//                System.out.println("logResult.data is not a valid JSON object");
//            }
//        }

//        }
//    public static String convertToJson(EthLog ethLog) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(ethLog);
//    }




















//        Web3j web3j = Web3j.build(new HttpService(INFURA_URL));
//        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, CONTRACT_ADDRESS);
//
//        CompletableFuture<EthLog> logFuture = web3j.ethGetLogs(filter).sendAsync();
//
//        logFuture.thenAccept(logs -> {
//            List<EthLog.LogResult> logResults = logs.getLogs();
//            for (EthLog.LogResult logResult : logResults) {
//                Log log = (Log) logResult.get();
//                System.out.println("New event: " + log);
//                String eventData = log.getData();
//                System.out.println("Raw Event Data: " + eventData);
//
//                // تبدیل داده هگزادسیمال به BigInteger
//                BigInteger blockNumberBigInt = DataConverter.hexToBigInteger(log.getBlockNumber().toString());
//                System.out.println("Block Number as BigInteger: " + blockNumberBigInt);
//
//                // استفاده از توابع DataConverter
//                String extractedString = extractString(eventData, 1606, 40);
//                System.out.println("Extracted String: " + extractedString);
//
//                String description = extractDescription(eventData);
//                System.out.println("Description: " + description);
//            }
//        }).exceptionally(ex -> {
//            ex.printStackTrace();
//            return null;
//        }).join();
//    }
//
//    private static String extractString(String data, int start, int length) {
//        return data.substring(start, start + length);
//    }
//
//    private static String hexToString(String hex) {
//        StringBuilder str = new StringBuilder();
//        for (int i = 0; i < hex.length(); i += 2) {
//            String part = hex.substring(i, i + 2);
//            int charCode = Integer.parseInt(part, 16);
//            if (charCode != 0) {
//                str.append((char) charCode);
//            }
//        }
//        return str.toString();
//    }
//
//    private static String extractDescription(String data) {
//        int jsonStartHex = data.indexOf("7b");
//        int jsonEndHex = data.indexOf("7d", jsonStartHex) + 2;
//
//        if (jsonStartHex != -1 && jsonEndHex != -1) {
//            String jsonHex = data.substring(jsonStartHex, jsonEndHex);
//            String jsonString = hexToString(jsonHex);
//            try {
//                JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
//                return jsonObject.has("description") ? jsonObject.get("description").getAsString() : "Description not found";
//            } catch (Exception e) {
//                return "Error parsing JSON";
//            }
//        } else {
//            return "JSON part not found";
//        }
//public static boolean isValidJson(String json) {
//    JsonParser parser = new JsonParser();
//    try {
//        parser.parse(json);
//        return true;
//    } catch (JsonParseException e) {
//        return false;
//    }
//}
//}