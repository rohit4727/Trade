package com.iris.kafkaconsumer.daoImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hazelcast.core.HazelcastInstance;
import com.iris.kafkaconsumer.constants.IConstants;
import com.iris.kafkaconsumer.dao.TradeDAO;
import com.iris.kafkaconsumer.model.Trade;
import com.iris.kafkaconsumer.repository.TradeRepository;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@Repository
public class TradeDAOImpl implements TradeDAO {

	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private HazelcastInstance hazelcastInstance;

	/*
	 * @return List of 10 trade according to given security
	 */
	@Override
	public List<Trade> getTradeListBySecurity(String security) {

		List<Trade> list = getCachedTradeList(security);

		if (list != null && list.size() >= 10)
			return new ArrayList<Trade>(list.subList(0, 10));
		list = tradeRepository.getTradesBySecurity(security);
		return (list != null && list.size() >= 10 ? list : tradeRepository.getTradesBySecurity());
	}

	/**
	 * @param security
	 * @param tradeDate
	 * @param tradeTime
	 * @return
	 */
	@Override
	public List<Trade> findTrade(String security, Date tradeDate, Date fromTime, Date toTime) {
		return tradeRepository.findBySecurityByTradeDateAndTradeTimeDuration(security, tradeDate, fromTime, toTime);
	}

	/**
	 * 
	 * @param security
	 * @return get List of 10 trade according to given security from cached list
	 */

	public List<Trade> getCachedTradeList(String security) {
		List<Trade> list = null;
		Map<Integer, Trade> map = hazelcastInstance.getMap(IConstants.GLOBAL_CACHE);
		if (security != null && security.equalsIgnoreCase(IConstants.DEFAULT_SECURITY))
			list = map.values().stream()
					.sorted(Comparator.comparing(Trade::getTradeDate).thenComparing(Trade::getTradeTime).reversed())
					.collect(Collectors.toList());
		else
			list = map.values().stream().filter(t -> t.getSecurity().equalsIgnoreCase(security))
					.sorted(Comparator.comparing(Trade::getTradeDate).thenComparing(Trade::getTradeTime).reversed())
					.collect(Collectors.toList());
		/*
		 * if (list == null || list.size()<10) return getFixedTradeList(); else
		 */
		return list;
	}

	/**
	 * @return get unique Security List
	 */
	public List<String> getSeurityList() {
		return tradeRepository.getSeurityList();
	}

	/*
	 * public List<Trade> getFixedTradeList() { List<Trade> list=new ArrayList<>();
	 * try { Trade t=new Trade(); t.setBroker("IndiaBulls"); t.setCurrency("INR");
	 * t.setInstrumentType("Equity"); t.setSecurity("IRIS"); t.setTradeDate(new
	 * SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018")); t.setTradeTime(new
	 * SimpleDateFormat("HH:mm:ss").parse("08:02:53")); t.setTradeId(14036);
	 * t.setTradePrice(103.93);
	 * 
	 * Trade t1=new Trade(); t1.setBroker("CITI"); t1.setCurrency("INR");
	 * t1.setInstrumentType("Equity"); t1.setSecurity("IRIS"); t1.setTradeDate(new
	 * SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018")); t1.setTradeTime(new
	 * SimpleDateFormat("HH:mm:ss").parse("08:38:29")); t1.setTradeId(16136);
	 * t1.setTradePrice(103.86);
	 * 
	 * Trade t3=new Trade(); t3.setBroker("IndiaBulls"); t3.setCurrency("INR");
	 * t3.setInstrumentType("Equity"); t3.setSecurity("IRIS"); t3.setTradeDate(new
	 * SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018")); t3.setTradeTime(new
	 * SimpleDateFormat("HH:mm:ss").parse("08:02:13")); t3.setTradeId(14037);
	 * t3.setTradePrice(102.93);
	 * 
	 * Trade t4=new Trade(); t4.setBroker("CITI"); t4.setCurrency("INR");
	 * t4.setInstrumentType("Equity"); t4.setSecurity("IRIS"); t4.setTradeDate(new
	 * SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018")); t4.setTradeTime(new
	 * SimpleDateFormat("HH:mm:ss").parse("08:38:59")); t4.setTradeId(16138);
	 * t4.setTradePrice(101.86);
	 * 
	 * Trade t5=new Trade(); t5.setBroker("IndiaBulls"); t5.setCurrency("INR");
	 * t5.setInstrumentType("Equity"); t5.setSecurity("IRIS"); t5.setTradeDate(new
	 * SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018")); t5.setTradeTime(new
	 * SimpleDateFormat("HH:mm:ss").parse("08:01:53")); t5.setTradeId(14039);
	 * t5.setTradePrice(100.93);
	 * 
	 * Trade t6=new Trade(); t6.setBroker("CITI"); t6.setCurrency("INR");
	 * t6.setInstrumentType("Equity"); t6.setSecurity("IRIS"); t6.setTradeDate(new
	 * SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018")); t6.setTradeTime(new
	 * SimpleDateFormat("HH:mm:ss").parse("08:28:29")); t6.setTradeId(16140);
	 * t6.setTradePrice(101.26);
	 * 
	 * Trade t7=new Trade(); t7.setBroker("IndiaBulls"); t7.setCurrency("INR");
	 * t7.setInstrumentType("Equity"); t7.setSecurity("IRIS"); t7.setTradeDate(new
	 * SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018")); t7.setTradeTime(new
	 * SimpleDateFormat("HH:mm:ss").parse("08:12:53")); t7.setTradeId(14041);
	 * t7.setTradePrice(103.53);
	 * 
	 * Trade t8=new Trade(); t8.setBroker("CITI"); t8.setCurrency("INR");
	 * t8.setInstrumentType("Equity"); t8.setSecurity("IRIS"); t8.setTradeDate(new
	 * SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018")); t8.setTradeTime(new
	 * SimpleDateFormat("HH:mm:ss").parse("08:18:29")); t8.setTradeId(16142);
	 * t8.setTradePrice(103.36);
	 * 
	 * Trade t9=new Trade(); t9.setBroker("IndiaBulls"); t9.setCurrency("INR");
	 * t9.setInstrumentType("Equity"); t9.setSecurity("IRIS"); t9.setTradeDate(new
	 * SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018")); t9.setTradeTime(new
	 * SimpleDateFormat("HH:mm:ss").parse("08:32:53")); t9.setTradeId(14043);
	 * t9.setTradePrice(108.93);
	 * 
	 * Trade t2=new Trade(); t2.setBroker("CITI"); t2.setCurrency("INR");
	 * t2.setInstrumentType("Equity"); t2.setSecurity("IRIS"); t2.setTradeDate(new
	 * SimpleDateFormat("dd-MM-yyyy").parse("05-07-2018")); t2.setTradeTime(new
	 * SimpleDateFormat("HH:mm:ss").parse("08:58:29")); t2.setTradeId(16144);
	 * t2.setTradePrice(102.46);
	 * 
	 * 
	 * list.add(t); list.add(t1); list.add(t2); list.add(t3); list.add(t4);
	 * list.add(t5); list.add(t6); list.add(t7); list.add(t8); list.add(t9); } catch
	 * (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * }
	 * 
	 * return list;
	 * 
	 * }
	 */

}
