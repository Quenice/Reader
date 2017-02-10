package com.quenice.reader.common.http.helper;

import com.quenice.reader.common.http.model.ZhihuDaily;
import com.quenice.reader.common.utils.Constants;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 请求接口
 * Created by qiubb on 2017/02/10.
 */
public interface HttpService {

	/**
	 * 获得直呼日报最新消息
	 *
	 * @return
	 */
	@GET(Constants.Urls.ZhiHuDaily.LIST)
	Observable<ZhihuDaily> zhihuDailyListLatest();
}
