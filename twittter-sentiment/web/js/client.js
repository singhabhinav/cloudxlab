var socket = io();
var hashtag = "";
var dataArr = [0, 0, 0];
//code will use high charts to draw the line graph using hash tag and their counts.
socket.on('twitterData', function(data){
	var json = getJsonObj(data);
	var tags=[];
	var counts=[];
	//Segregating the hash tags and counts into separate array's.
	for (i in json.tweets) {
		tags.push(json.tweets[i].tag)
		counts.push(json.tweets[i].count)
	}
	updateChart('#chartHolder', 'spline', 'Popular hash tags in the last 60 seconds', 'Source: twitter.com', 'Tweets', tags, 'Counts', 'Tweet Counts', counts);
})

//Code will append the tweets into the ui by highlighting them with bootstrap css class(based on sentiment value)
socket.on('sentimentData', function(data){
	if(hashtag.length > 0) {
		var json = getJsonObj(data);
		var cssClass = "alert alert-warning";
		switch(json.sentiment) {
			case "NEGATIVE": 
				cssClass = "alert alert-danger";
				break;
			case "NEUTRAL":
				cssClass = "alert alert-neutral";
				break;
			case "VERY_POSITIVE":
			case "POSITIVE":
				cssClass = "alert alert-success";				
		}	
		var res = $("div:contains(" + json.tweet + ")");
		var res1 = json.tweet.toLowerCase().includes("#" + hashtag);
		if(res.length == 0 && res1) {
			var divContent = "<div class=\"textColor\">" + json.sentiment + "</div><div class=\"" + cssClass + "\">" + json.tweet + "</div>";
			$('#sentimentHolder').append(divContent);
			$("#sentimentPanelBody").animate({ scrollTop: $('#sentimentPanelBody').prop("scrollHeight")}, 1000);
			if(json.sentiment == "POSITIVE" || json.sentiment == "VERY_POSITIVE") {
				dataArr[0]++;
			} else if(json.sentiment == "NEGATIVE") {
				dataArr[1]++;
			} else if(json.sentiment == "NEUTRAL") {
				dataArr[2]++;
			}
			updateChart('#sentimentHolderGraph', 'column', 'Sentiment Analysis Graph on Hash Tag - ' + hashtag, 'Source: twitter.com', 'Sentiment Types', ['Positive', 'Negative', 'Neutral'], 'Counts', 'Postive, Negative and Neutral Counts', dataArr);
		}
	}	
})

function getJsonObj(message) {
	//Bypassing new line, tab etc., characters from json parser, otherwise json parser will throw error.
	message = message.replace(/[\b]/g, '\\b').replace(/[\f]/g, '\\f').replace(/[\n]/g, '\\n').replace(/[\r]/g, '\\r').replace(/[\t]/g, '\\t');
	var json = JSON.parse(message);
	return json;
}

function setHashTag() {
	var tempHashTag = hashtag;
	hashtag = $('#hashTagId').val();
	hashtag = hashtag.toLowerCase();
	if(tempHashTag != hashtag) {
		$('#sentimentHolder div').remove();
		$('#sentimentHolderGraph').empty();
		dataArr = [0, 0, 0];
	}
}

function updateChart(id, chartType, titleText, subtitleText, xAxisTitleText, categoriesData, yAxisTitleText, seriesName, seriesData) {
	$(id).highcharts({
		chart: {
			type: chartType,
			animation: Highcharts.svg,
			marginRight: 10,
			events: {
				load: function () {
					chartHolder = this;
				}
			}
		},
		title: {
			text: titleText
		},
		subtitle: {
			text: subtitleText
		},
		xAxis: {
			title: {
				text: xAxisTitleText
			},
			categories: categoriesData
		},
		yAxis: {
		  title: {
			 text: yAxisTitleText
		  }
		},
		plotOptions: {
			line: {
				 dataLabels: {
					enabled: true
				 },   
				 enableMouseTracking: false
		   }
		},
		exporting: {
			enabled: true
		},
		credits: {
			enabled: false
		},
		series:  [
		   {
			 name: seriesName,
			 data: seriesData
		   }
		]
	});
}