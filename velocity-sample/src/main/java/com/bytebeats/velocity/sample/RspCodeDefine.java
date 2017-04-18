package com.bytebeats.velocity.sample;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class RspCodeDefine {

	private String default_tips = "您填写的银行卡未能审核通过，为了不耽误您用钱，请更换银行卡尽快重新提交申请。";

	private String bank_tips = "您填写的银行卡目前无法接收借款，请您更换他行银行卡重试。";

	String sql = "INSERT INTO tb_cbm_yxd_loan_error_code(code, message, type, create_time, modify_time) VALUES ('%s', '%s', 1, NOW(), NOW());";

	public static void main(String[] args) throws IOException {

		new RspCodeDefine().genSQL();
	}

	public void genSQL() throws IOException {

		Set<String> bankErrorCode = readCode(new File("C:\\Users\\Administrator\\Desktop\\resp_code.txt"));

		System.out.println(bankErrorCode.size());

		for(String code : bankErrorCode){
			if("3216".equals(code)){
				System.out.println(String.format(sql, code, bank_tips));
			} else {
				System.out.println(String.format(sql, code, default_tips));
			}
		}
	}

	public Set<String> readCode(File file) throws IOException {
		Set<String> bankErrorCode = new HashSet<>(88);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		String line = null;
		while((line=br.readLine())!=null){
			bankErrorCode.add(line);
		}
		return bankErrorCode;
	}

}
