package com.lxit.loans.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lxit.p2p.bean.AllLoans;
import com.lxit.p2p.bean.Applyloans;
import com.lxit.p2p.bean.OverdueLoans;
import com.lxit.p2p.bean.Refundplan;

@Repository
public interface ApplyLoansDao {
	int insertLoans(Applyloans applyLoans);
	List<Applyloans> queryLoansByState(Map<String,Object> map);
	long sumCount(Map<String,Object> sumMap);
	Applyloans queryLoansById(int applyId);
	
	int insertRepayLoans(Refundplan refundplan);
	
	int updateRepayByState(int refundplanId);
	int updateLoansByState(int applyId);
	long selectLoansByRepay(int refundplanId);
	
	List<Refundplan> queryRepayLoans(Map<String,Object> map);
	long sumCountRepay(int loansId);
	
	List<OverdueLoans> selectOverdueLoans(Map<String,Object> overMap);
	long sumOverdueCount(int userId);
	
	AllLoans queryAllLoans(int userId);
}
