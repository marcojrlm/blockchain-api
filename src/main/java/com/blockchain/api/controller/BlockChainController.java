package com.blockchain.api.controller;

import com.blockchain.api.Entity.Block;
import com.blockchain.api.Entity.Blockchain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class BlockChainController {

    Blockchain blockchain = new Blockchain(4);

    @GetMapping("blockchain")
    public ResponseEntity<List<Block>> getBlockChain() {
        return ResponseEntity.ok(blockchain.displayBlockchain());
    }

    @PostMapping("blockchain")
    public ResponseEntity<Integer> addBlockIntoChain(@RequestBody String data) {
        Block lastBlock = blockchain.getLatestBlock();
        blockchain.addBlock(
                new Block(
                        lastBlock.getId() + 1,
                        data,
                        lastBlock.getHash()
                )
        );
        return ResponseEntity.status(201).body(blockchain.getLatestBlock().getId() + 1);
    }
}
