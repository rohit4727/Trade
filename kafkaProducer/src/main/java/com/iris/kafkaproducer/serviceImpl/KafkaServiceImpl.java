package com.iris.kafkaproducer.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.iris.kafkaproducer.constants.IControllerConstants;
import com.iris.kafkaproducer.constants.TradeFields;
import com.iris.kafkaproducer.model.Trade;
import com.iris.kafkaproducer.service.KafkaService;
import com.iris.kafkaproducer.util.CSVMapping;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 *
 */
@Service
public class KafkaServiceImpl implements KafkaService {

	static Logger logger = Logger.getLogger(KafkaServiceImpl.class.getName());

	@Autowired
	private KafkaTemplate<String, Trade> kafkaTemplate;
	
	@Autowired
	CSVMapping csvMapping;

	/**
	 * 
	 * produce Trade on Kafka cluster
	 */
	@Override
	public String produceTrade() {
		String result = IControllerConstants.SUCCESS;
		try {
			Files.lines(Paths.get(IControllerConstants.CSV_FILE)).filter(t -> {
				String[] cells =null;
				try {
						Trade trade = new Trade();
						cells = t.split(",");
						//System.out.println("--------------tttttt-----------"+csvMapping.getTotalColumns()+csvMapping.getCsvProperty());
						for(int i=0; i<csvMapping.getTotalColumns();i++)
						{
							String colVal=csvMapping.getCsvProperty().get(i+1);
							//System.out.println("--------------------------"+colVal);
							if(colVal.equalsIgnoreCase(TradeFields.TradeId.toString()))
								trade.setTradeId(Integer.parseInt(cells[i]));
							if(colVal.equalsIgnoreCase(TradeFields.TradePrice.toString()))
								trade.setTradePrice(Double.parseDouble(cells[i]));
							if(colVal.equalsIgnoreCase(TradeFields.Security.toString()))
								trade.setSecurity(cells[i]);
							if(colVal.equalsIgnoreCase(TradeFields.InstumentType.toString()))
								trade.setInstrumentType(cells[i]);
							if(colVal.equalsIgnoreCase(TradeFields.TradeDate.toString()))
								trade.setTradeDate(new SimpleDateFormat(IControllerConstants.DATE_FORMATE).parse(cells[i]));
							if(colVal.equalsIgnoreCase(TradeFields.TradeTime.toString()))
								trade.setTradeTime(new SimpleDateFormat(IControllerConstants.TIME_FORMATE).parse(cells[i]));
							if(colVal.equalsIgnoreCase(TradeFields.Currency.toString()))
								trade.setCurrency(cells[i]);
							if(colVal.equalsIgnoreCase(TradeFields.Broker.toString()))
								trade.setBroker(cells[i]);
							if(colVal.equalsIgnoreCase(TradeFields.Direction.toString()))
								trade.setDirection(cells[i]);
						}	
						//System.out.println("--------------------------"+trade);
						kafkaTemplate.send(IControllerConstants.TOPIC, trade);
						Thread.sleep(500);
						
				} catch (Exception e) {
					logger.log(Level.INFO, e.getMessage()+" Issue with date or Time formate"+cells.toString());
				}
				return true;
			}).count();
		} catch (IOException e) {
			logger.log(Level.ALL, "Producer issue " + e.getMessage()+" May be "+IControllerConstants.CSV_FILE +" not found!");
			result = IControllerConstants.FAILURE;
		}
		
		return result;
	}

}
