package com.lxit.loans.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lxit.loans.service.ApplyLoansService;
import com.lxit.p2p.bean.AllLoans;
import com.lxit.p2p.bean.Applyloans;
import com.lxit.p2p.bean.OverdueLoans;
import com.lxit.p2p.bean.Pager;
import com.lxit.p2p.bean.Refundplan;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.lxit.loans.service" })
public class ApplyLoansAction {

	@Resource
	ApplyLoansService applyLoansService;

	@ResponseBody
	@RequestMapping("/addLoans")
	public String getAddLoans(@RequestBody @Valid Applyloans applyLoans, BindingResult errorResult) {
		System.out.println(applyLoans + "================");

		if (errorResult.hasErrors()) {
			// result.rejectValue("name", "NotEmpty", "名称不能为空");
			for (ObjectError error : errorResult.getAllErrors()) {
				// System.err.println(error.getObjectName() + ":" +
				// error.getCode() + "-->" + error.getDefaultMessage());
				return error.getDefaultMessage();
			}
		}
		if (applyLoansService.insertLoans(applyLoans) > 0) {
			return "true";
		}
		return "false";
	}
	
	@ResponseBody
	@RequestMapping("/queryLoans")
	public Pager<Applyloans> getQueryLoans(@RequestParam("state") String state,@RequestParam("userId") int userId,@RequestParam("pageIndex") int pageIndex,@RequestParam("pageSize") int pageSize){
		Map<String,Object> queryLoasMap = new HashMap<String,Object>();
		Pager<Applyloans> pagerLoans = new Pager<Applyloans>();
		queryLoasMap.put("state", state);
		queryLoasMap.put("userId", userId);
		queryLoasMap.put("pageIndex", (pageIndex-1)*pageSize);
		queryLoasMap.put("pageSize", pageSize);
		
		pagerLoans.setPageIndex(pageIndex);
		pagerLoans.setPageSize(pageSize);
		pagerLoans.setSumCount((int)getSumCount(state,userId));
		
		pagerLoans.setData(applyLoansService.queryLoansByState(queryLoasMap));
		
		return pagerLoans;
	}
	
	public long getSumCount(@RequestParam("state") String state,@RequestParam("userId") int userId){
		Map<String,Object> queryLoasSumMap = new HashMap<String,Object>();
		queryLoasSumMap.put("state", state);
		queryLoasSumMap.put("userId", userId);
		return applyLoansService.sumCount(queryLoasSumMap);
	}
	
	@RequestMapping("/queryLoansById")
	public Applyloans getLoansById(@RequestParam("applyId") int applyId){
		return applyLoansService.queryLoansById(applyId);
	}
	
	//还款表增加
	@RequestMapping("/addRepayLoans")
	@ResponseBody
	public String getAddRepay(@RequestBody Refundplan refundplan){
		if(applyLoansService.insertRepayLoans(refundplan) > 0){
			return "true";
		}else{
			return "false";
		}
	}
	
	//还款表查询
	@RequestMapping("/queryRepay")
	@ResponseBody
	public Pager<Refundplan> getQueryRepay(@RequestParam("applyId")int applyId,@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize){
		Map<String,Object> repayMap = new HashMap<String,Object>();
		Pager<Refundplan> repayPager = new Pager<Refundplan>();
		
		repayMap.put("applyId", applyId);
		repayMap.put("pageIndex", (pageIndex-1)*pageSize);
		repayMap.put("pageSize", pageSize);
		
		repayPager.setPageIndex(pageIndex);
		repayPager.setPageSize(pageSize);
		repayPager.setSumCount((int)getSumRepay(applyId));
		
		repayPager.setData(applyLoansService.queryRepayLoans(repayMap));
		
		return repayPager;
	}
	public long getSumRepay(int applyId){
		return applyLoansService.sumCountRepay(applyId);
	}
	
	@RequestMapping("/updateRepay")
	@ResponseBody
	public String getUpdateRepay(@RequestParam("refundplanId") int refundplanId){
		if(applyLoansService.updateRepayByState(refundplanId) > 0){
			if(applyLoansService.selectLoansByRepay(refundplanId) == 0){
				applyLoansService.updateLoansByState(refundplanId);
			}
			return "true";
		}else{
			return "false";
		}
	}
	
	@RequestMapping("/queryOverLoans")
	@ResponseBody
	public Pager<OverdueLoans> getQueryOverLoans(@RequestParam("userId") int userId,@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize){
		Map<String,Object> overMap = new HashMap<String,Object>();
		Pager<OverdueLoans> overPager = new Pager<OverdueLoans>();
		
		overMap.put("userId", userId);
		overMap.put("pageIndex", (pageIndex-1)*pageSize);
		overMap.put("pageSize", pageSize);
		
		overPager.setPageIndex(pageIndex);
		overPager.setPageSize(pageSize);
		overPager.setSumCount((int)getSumOver(userId));
		
		overPager.setData(applyLoansService.selectOverdueLoans(overMap));
		
		return overPager;
	}
	
	public long getSumOver(int userId){
		return applyLoansService.sumOverdueCount(userId);
	}
	
	@RequestMapping("/queryAllLoans")
	public AllLoans getQueryAllLoans(@RequestParam("userId")int userId){
		return applyLoansService.queryAllLoans(userId);
	}

}
