function displayDayLine()
{
	if (ajaxObj7.readyState == 4)
	{
		if (ajaxObj7.status == 200)
		{
			var datas = ajaxObj7.responseText;
			var tdata = eval(datas);
			var data = tdata;
			//dayline
			var ohlc = [],
			volume = [],
			dataLength = data.length;
			
			for (i = 0; i < dataLength; i++) {
				ohlc.push([
					data[i][0], // the date
					data[i][1], // open
					data[i][2], // high
					data[i][3], // low
					data[i][4] // close
				]);
				
				volume.push([
					data[i][0], // the date
					data[i][5] // the volume
				])
			}
	
			// set the allowed units for data grouping
			var groupingUnits = [[
				'week',                         // unit name
				[1]                             // allowed multiples
			], [
				'month',
				[1, 2, 3, 4, 6]
			]];
	
			// create the chart
			$('#k-line').highcharts('StockChart', {
			    
			    rangeSelector: {
			        selected: 1
			    },
	
			    title: {
			        text: 'JAVA币交易统计'
			    },
	
			    yAxis: [{
			        title: {
			            text: '价格'
			        },
			        height: 300,
			        lineWidth: 2
			    }, {
			        title: {
			            text: '成交量'
			        },
			        top: 300,
			        height: 150,
			        offset: 0,
			        lineWidth: 2
			    }],
			    
			    series: [{
			        type: 'candlestick',
			        name: 'JAVA币',
			        data: ohlc,
			        dataGrouping: {
						units: groupingUnits
			        }
			    }, {
			        type: 'column',
			        name: '成交量',
			        data: volume,
			        yAxis: 1,
			        dataGrouping: {
						units: groupingUnits
			        }
			    }]
			});
		
		}
	}
}



function displayTimeLine()
{
	if (ajaxObj6.readyState == 4)
	{
		if (ajaxObj6.status == 200)
		{
			var datas = ajaxObj6.responseText;
			var tdata = eval(datas);
			
			var data = tdata;
			//dayline
			var ohlc = [],
			volume = [],
			dataLength = data.length;
			
			for (i = 0; i < dataLength; i++) {
				ohlc.push([
					data[i][0], // the date
					data[i][1], // open
					data[i][2], // high
					data[i][3], // low
					data[i][4] // close
				]);
				
				volume.push([
					data[i][0], // the date
					data[i][5] // the volume
				])
			}
	
			// set the allowed units for data grouping
			var groupingUnits = [[
				'week',                         // unit name
				[1]                             // allowed multiples
			], [
				'month',
				[1, 2, 3, 4, 6]
			]];
	
			// create the chart
			$('#k-line').highcharts('StockChart', {
			    
			    rangeSelector: {
			        selected: 1
			    },
	
			    title: {
			        text: 'JAVA币交易统计'
			    },
	
			    yAxis: [{
			        title: {
			            text: '价格'
			        },
			        height: 300,
			        lineWidth: 2
			    }, {
			        title: {
			            text: '成交量'
			        },
			        top: 300,
			        height: 150,
			        offset: 0,
			        lineWidth: 2
			    }],
			    
			    series: [{
			        type: 'candlestick',
			        name: '<%=session.getAttribute("btc_stock_name").toString()%>',
			        data: ohlc,
			        dataGrouping: {
						units: groupingUnits
			        }
			    }, {
			        type: 'column',
			        name: '成交量',
			        data: volume,
			        yAxis: 1,
			        dataGrouping: {
						units: groupingUnits
			        }
			    }]
			});
		}
	}
}

function display5minLine()
{
	if (ajaxObj8.readyState == 4)
	{
		if (ajaxObj8.status == 200)
		{
			var dates = ajaxObj8.responseText;
			var tdate = eval(dates);
			var data=tdate;
			
			//dayline
			var ohlc = [],
			volume = [],
			dataLength = data.length;
			
			for (i = 0; i < dataLength; i++) {
				ohlc.push([
					data[i][0], // the date
					data[i][1], // open
					data[i][2], // high
					data[i][3], // low
					data[i][4] // close
				]);
				
				volume.push([
					data[i][0], // the date
					data[i][5] // the volume
				])
			}
	
			// set the allowed units for data grouping
			var groupingUnits = [[
				'week',                         // unit name
				[1]                             // allowed multiples
			], [
				'month',
				[1, 2, 3, 4, 6]
			]];
	
			// create the chart
			$('#k-line').highcharts('StockChart', {
			    
			    rangeSelector: {
			        selected: 1
			    },
	
			    title: {
			        text: 'JAVA币交易统计'
			    },
	
			    yAxis: [{
			        title: {
			            text: '价格'
			        },
			        height: 300,
			        lineWidth: 2
			    }, {
			        title: {
			            text: '成交量'
			        },
			        top: 300,
			        height: 150,
			        offset: 0,
			        lineWidth: 2
			    }],
			    
			    series: [{
			        type: 'candlestick',
			        name: '<%=session.getAttribute("btc_stock_name").toString()%>',
			        data: ohlc,
			        dataGrouping: {
						units: groupingUnits
			        }
			    }, {
			        type: 'column',
			        name: '成交量',
			        data: volume,
			        yAxis: 1,
			        dataGrouping: {
						units: groupingUnits
			        }
			    }]
			});
		}
	}
}
