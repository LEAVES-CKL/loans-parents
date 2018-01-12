package com.lxit.loans.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

import com.lxit.loans.dao.ApplyLoansDao;
import com.lxit.loans.service.ApplyLoansService;
import com.lxit.p2p.bean.AllLoans;
import com.lxit.p2p.bean.Applyloans;
import com.lxit.p2p.bean.OverdueLoans;
import com.lxit.p2p.bean.Refundplan;

@Service
@MapperScan("com.lxit.loans.dao")
public class ApplyLoansServiceImpl implements ApplyLoansService {
	
	@Resource
	ApplyLoansDao applyLoansDao;

	@Override
	public int insertLoans(Applyloans applyLoans) {
		return applyLoansDao.insertLoans(applyLoans);
	}

	@Override
	public List<Applyloans> queryLoansByState(Map<String, Object> map) {
		return applyLoansDao.queryLoansByState(map);
	}

	@Override
	public long sumCount(Map<String, Object> sumMap) {
		return applyLoansDao.sumCount(sumMap);
	}

	@Override
	public Applyloans queryLoansById(int applyId) {
		return applyLoansDao.queryLoansById(applyId);
	}

	@Override
	public int insertRepayLoans(Refundplan refundplan) {
		return applyLoansDao.insertRepayLoans(refundplan);
	}

	@Override
	public List<Refundplan> queryRepayLoans(Map<String, Object> map) {
		return applyLoansDao.queryRepayLoans(map);
	}

	@Override
	public long sumCountRepay(int loansId) {
		return applyLoansDao.sumCountRepay(loansId);
	}

	@Override
	public List<OverdueLoans> selectOverdueLoans(Map<String, Object> overMap) {
		return applyLoansDao.selectOverdueLoans(overMap);
	}

	@Override
	public long sumOverdueCount(int userId) {
		return applyLoansDao.sumOverdueCount(userId);
	}

	@Override
	public AllLoans queryAllLoans(int userId) {
		return applyLoansDao.queryAllLoans(userId);
	}

	@Override
	public int updateRepayByState(int refundplanId) {
		return applyLoansDao.updateRepayByState(refundplanId);
	}

	@Override
	public int updateLoansByState(int applyId) {
		return applyLoansDao.updateLoansByState(applyId);
	}

	@Override
	public long selectLoansByRepay(int refundplanId) {
		return applyLoansDao.selectLoansByRepay(refundplanId);
	}

}
