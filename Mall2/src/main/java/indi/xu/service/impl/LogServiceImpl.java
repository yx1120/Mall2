package indi.xu.service.impl;

import indi.xu.dao.LogDao;
import indi.xu.domain.Log;
import indi.xu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author a_apple
 * @create 2020-03-08 21:10
 */

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void addLog(Log log) {
        logDao.addLog(log);
    }
}
