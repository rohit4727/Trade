package com.iris.kafkaproducer.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.iris.kafkaproducer.constants.IControllerConstants;
import com.iris.kafkaproducer.model.Trade;
import com.iris.kafkaproducer.service.KafkaService;

@Service
public class KafkaServiceImpl implements KafkaService {

	static Logger logger = Logger.getLogger(KafkaServiceImpl.class.getName());
	
	@Autowired
	private KafkaTemplate<String, Trade> kafkaTemplate;
	
	@Override
	public String produceTrade() {
		String result=IControllerConstants.SUCCESS;
		try 
    	{
			Files.lines(Paths.get(IControllerConstants.CSV_FILE)).filter(t->
			{
				try
				{
					Trade trade=new Trade();
					String[] cells=t.split(",");
					trade.setTradeId(Integer.parseInt(cells[0]));
					trade.setTradePrice(Double.parseDouble(cells[1]));
					trade.setSecurity(cells[2]);
					trade.setInstrumentType(cells[3]);
					trade.setTradeDate(new SimpleDateFormat("dd-MM-yyyy").parse(cells[4]));
					trade.setTradeTime(new SimpleDateFormat("HH:mm:ss").parse(cells[5]));
					trade.setCurrency(cells[6]);
					trade.setBroker(cells[7]);
					
					kafkaTemplate.send(IControllerConstants.TOPIC,trade);
				}
				catch (Exception e) {
					logger.log(Level.INFO, e.getMessage());
				}
				return true;
			}).count();
		} catch (IOException e) {
			logger.log(Level.ALL, e.getMessage());
			result=IControllerConstants.FAILURE;
		}
    	return result;
	}

	
}
