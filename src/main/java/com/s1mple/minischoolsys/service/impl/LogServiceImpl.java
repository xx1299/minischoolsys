package com.s1mple.minischoolsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.s1mple.minischoolsys.dao.LogMapper;
import com.s1mple.minischoolsys.domain.Log;
import com.s1mple.minischoolsys.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
}
