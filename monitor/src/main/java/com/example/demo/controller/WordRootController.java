package com.example.demo.controller;

import com.example.demo.entity.WordRootRefer;
import com.example.demo.entity.WordRootSpec;
import com.example.demo.mapper.WordRootReferMapper;
import com.example.demo.mapper.WordRootSpecMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wordroots")
public class WordRootController {
    private final WordRootSpecMapper specMapper;
    private final WordRootReferMapper referMapper;

    public WordRootController(WordRootSpecMapper specMapper, WordRootReferMapper referMapper) {
        this.specMapper = specMapper;
        this.referMapper = referMapper;
    }

    @GetMapping("/specs/root/{root}")
    public WordRootSpec getRootSpec(@PathVariable String root) {
        return specMapper.findByRoot(root);
    }

    // WordRootSpec endpoints
    @GetMapping("/specs")
    public List<WordRootSpec> getAllSpecs() {
        return specMapper.findAll();
    }

    @GetMapping("/specs/{id}")
    public WordRootSpec getSpecById(@PathVariable Integer id) {
        return specMapper.findById(id);
    }

    @PostMapping("/specs")
    public int createSpec(@RequestBody WordRootSpec spec) {
        return specMapper.insert(spec);
    }

    @PutMapping("/specs/{id}")
    public int updateSpec(@PathVariable Integer id, @RequestBody WordRootSpec spec) {
        spec.setId(id);
        return specMapper.update(spec);
    }

    @DeleteMapping("/specs/{id}")
    public int deleteSpec(@PathVariable Integer id) {
        return specMapper.delete(id);
    }

    // WordRootRefer endpoints
    @GetMapping("/refers")
    public List<WordRootRefer> getAllRefers() {
        return referMapper.findAll();
    }

    @GetMapping("/refers/{id}")
    public WordRootRefer getReferById(@PathVariable Integer id) {
        return referMapper.findById(id);
    }

    @GetMapping("/refers/word/{word}")
    public List<WordRootRefer> getRefersByWord(@PathVariable String word) {
        return referMapper.findByWord(word);
    }

    @GetMapping("/refers/root/{root}")
    public List<WordRootRefer> getRefersByRoot(@PathVariable String root) {
        return referMapper.findByRoot(root);
    }

    @PostMapping("/refers")
    public int createRefer(@RequestBody WordRootRefer refer) {
        return referMapper.insert(refer);
    }

    @PutMapping("/refers/{id}")
    public int updateRefer(@PathVariable Integer id, @RequestBody WordRootRefer refer) {
        refer.setId(id);
        return referMapper.update(refer);
    }

    @DeleteMapping("/refers/{id}")
    public int deleteRefer(@PathVariable Integer id) {
        return referMapper.delete(id);
    }
}
