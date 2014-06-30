package com.mvc.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class MatchCommonUtil {
	//确定成交价，根据撮合算法：
	//如果前一笔成交价低于或等于卖出价，则最新成交价就是卖出价
	//如果前一笔成交价高于或等于买入价，则最新成交价就是买入价
	//如果前一笔成交价在卖出价与买入价之间，则最新成交价就是前一笔的成交价
	public BigDecimal getDealRate(BigDecimal latestDealRate,BigDecimal buyingRate,BigDecimal sellingRate ){
		BigDecimal dealRate = new BigDecimal(0);
		if(latestDealRate.compareTo(buyingRate)>=0){
			dealRate = buyingRate;
		}else if(latestDealRate.compareTo(sellingRate)<=0){
			dealRate = sellingRate;
		}else if(latestDealRate.compareTo(buyingRate)==-1||latestDealRate.compareTo(sellingRate)==1){
			dealRate = latestDealRate;
		}
		return dealRate;
	}
	

}
