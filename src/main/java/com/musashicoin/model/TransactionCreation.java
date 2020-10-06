package com.musashicoin.model;

import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;

import blockchainclasses.Block;
import blockchainclasses.Transaction;
import blockchainclasses.TransactionOutput;
import blockchainclasses.Wallet;

public class TransactionCreation {

	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static HashMap<String, TransactionOutput> UTXOs = new HashMap<>(); //List of all unspent transactions. 
	
	public static int difficulty = 3;
	public static float minimumTransaction = 0.1f;
	
	public static Transaction genesisTransaction;
	
	public static Wallet coinbase;
	public static Wallet walletA;
	public static Wallet walletB;
	public static Block genesis;
	
	public void createGenesisTransaction() {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		coinbase = new Wallet();
		walletA = new Wallet();
		
		genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 100f, null);
		genesisTransaction.generateSignature(coinbase.privateKey);
		genesisTransaction.transactionId = "0";
		genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId));
		UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));
		
		System.out.println("Creating and mining Genesis block...");
		genesis = new Block("0");
		genesis.addTransaction(genesisTransaction);
		addBlock(genesis);
	}
	
	public void createNewTransaction() {
		Block block1 = new Block(genesis.hash);
		walletB = new Wallet();
		
		System.out.println("\nWwalletA's balance is: " + walletA.getBalance());
		System.out.println("\nWalletA is attempting to send funds (40) to WalletB...");
		block1.addTransaction(walletA.sendFunds(walletB.publicKey, 40f));
		addBlock(block1);
		System.out.println("\nWalletA's balance is: " + walletA.getBalance());
		System.out.println("WalletB's balance is: " + walletB.getBalance());
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
	
}
