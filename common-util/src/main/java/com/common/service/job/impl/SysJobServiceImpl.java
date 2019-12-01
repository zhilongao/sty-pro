package com.common.service.job.impl;

import com.common.dao.SysJobMapper;
import com.common.entity.SysJob;
import com.common.service.job.ISysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SysJobServiceImpl implements ISysJobService {

	@Autowired
	private SysJobMapper sysJobMapper;

	/**
	 * 获取job总数
	 * @return
	 */
	@Override
	public int getJobCount() {
		return sysJobMapper.getJobCount();
	}

	/**
	 * 查询job列表
	 * @param map
	 * @return
	 */
	@Override
	public List<SysJob> querySysJobList(HashMap<String, String> map) {
		return sysJobMapper.querySysJobList(map);
	}

	/**
	 * 添加job
	 * @param record
	 * @return
	 */
	@Override
	public int insertSelective(SysJob record) {
		return sysJobMapper.insertSelective(record);
	}

	/**
	 * 删除job
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysJobMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 通过主键查询job
	 * @param id
	 * @return
	 */
	@Override
	public SysJob selectByPrimaryKey(Integer id) {
		return sysJobMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysJob selectByBean(SysJob bean) {
		return sysJobMapper.selectByBean(bean);
	}

	@Override
	public int updateByPrimaryKeySelective(SysJob bean) {
		return sysJobMapper.updateByPrimaryKeySelective(bean);
	}
}
