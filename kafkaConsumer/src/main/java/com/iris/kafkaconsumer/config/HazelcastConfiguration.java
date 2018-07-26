package com.iris.kafkaconsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.iris.kafkaconsumer.constants.IConstants;

/**
 * 
 * @Date : 23-Jul-2018
 * @Author : Rohit Chauhan
 */
@Configuration
public class HazelcastConfiguration {

	/**
	 * 
	 * @return HazelCast Cache configuration
	 */
	@Bean
	public Config hazelCastConfig() {
		return new Config().setInstanceName(IConstants.HAZELCAST_INSTANCE)
				.addMapConfig(new MapConfig().setName(IConstants.CACHE_NAME)
						// .setMaxSizeConfig(new MaxSizeConfig(200,
						// MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
						.setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(-1));

	}

}
