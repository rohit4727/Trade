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
import com.iris.kafkaproducer.model.Trade;
import com.iris.kafkaproducer.service.KafkaService;
import com.iris.kafkaproducer.util.CSVMapping;
import com.iris.kafkaproducer.constants.TradeFields;

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
	private CSVMapping csvMapping;
	private static int lineNo=0;

	/**
	 * 
	 * produce Trade on Kafka cluster
	 */
	@Override
	public String produceTrade() {
		String result = IControllerConstants.SUCCESS;
		try {// csvMapping.getFileName()
			Files.lines(Paths.get(IControllerConstants.CSV_FILE)).filter(t -> {

				lineNo++;
				if(lineNo==1) {
					return true;
				}
				String[] cells =null;
				try {
						Trade trade = new Trade();
						cells = t.split(",");
						String colVal=null;
						
						for(int i=0; i<csvMapping.getTotalColumns();i++)
						{
							colVal=csvMapping.getCsvProperty().get(i+1);
													
							switch(TradeFields.valueOf(colVal))
					        {
					            case TradeId:
					            	trade.setTradeId(Integer.parseInt(cells[i]));
					                break;
					            case TradePrice:
					            	trade.setTradePrice(Double.parseDouble(cells[i]));
					                break;
					            case Security:
					            	trade.setSecurity(cells[i]);
					                break;
					            case InstumentType:
					            	trade.setInstrumentType(cells[i]);
					                break;
					            case TradeDate:
					            	trade.setTradeDate(new SimpleDateFormat(IControllerConstants.DATE_FORMATE).parse(cells[i]));
					                break;
					            case TradeTime:
					            	trade.setTradeTime(new SimpleDateFormat(IControllerConstants.TIME_FORMATE).parse(cells[i]));
					                break;
					            case Currency:
					            	trade.setCurrency(cells[i]);
					                break;
					            case Broker:
					            	trade.setBroker(cells[i]);
					                break;
					            case Direction:
					            	trade.setDirection(cells[i]);
					                break;
					            
					        }
						}	
						//System.out.println("--------------------------"+trade);
						kafkaTemplate.send(IControllerConstants.TOPIC, trade);
						Thread.sleep(200);
						
				} catch (Exception e) {
					logger.log(Level.INFO, e.getMessage()+" Issue with date or Time formate"+cells.toString());
				}
				return true;
			}).count();
		} catch (IOException e) {
			logger.log(Level.ALL, "Producer issue " + e.getMessage()+" May be "+csvMapping.getFileName() +" not found!");
			System.out.println("Producer issue " + e.getMessage()+" May be "+csvMapping.getFileName() +" not found!");
			result = IControllerConstants.FAILURE;
		}
		
		return result;
	}

}
