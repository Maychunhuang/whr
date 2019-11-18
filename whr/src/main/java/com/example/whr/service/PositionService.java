package com.example.whr.service;

import com.example.whr.bean.Position;
import com.example.whr.dao.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 添加事务
 */
@Transactional
@Service
public class PositionService {
    @Autowired
    private PositionMapper positionMapper;

    public List<Position> getAllPos() {
        return positionMapper.getAllPos();
    }

    public int addPos(Position pos) {
        if (getPosByName(pos.getName()) != null) {
            return -1;
        }
        return positionMapper.addPos(pos);
    }

    public Position getPosByName(String name) {
        return positionMapper.getPosByName(name);
    }

    public boolean deletePosById(String pids) {
        String[] split = pids.split(",");
        //判断所受影响的行数与数组的长度是否一致
        return positionMapper.deletePosById(split) == split.length;
    }

    public int updatePosById(Position position) {
        return positionMapper.updatePosById(position);
    }
}
