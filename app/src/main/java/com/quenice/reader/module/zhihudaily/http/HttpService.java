package com.quenice.reader.module.zhihudaily.http;

import com.quenice.reader.module.zhihudaily.model.ZhihuDailyDetail;
import com.quenice.reader.module.zhihudaily.model.ZhihuDaily;
import com.quenice.reader.common.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by qiubb on 2017/2/22.
 */

public interface HttpService {
	/**
	 * 获得直呼日报最新消息
	 *
	 * @return
	 */
	@GET(Constants.Urls.ZhiHuDaily.LIST_LATEST)
	Observable<ZhihuDaily> listLatest();

	/**
	 * 获得知乎日报指定日期之前的消息
	 * @param date
	 * @return
	 */
	@GET(Constants.Urls.ZhiHuDaily.LIST_BEFORE + "/{date}")
	Observable<ZhihuDaily> listBefore(@Path("date") String date);

	/**
	 * 获得知乎日报详细消息
	 * @param id
	 * @return
	 */
	@GET(Constants.Urls.ZhiHuDaily.DETAIL + "/{id}")
	Observable<ZhihuDailyDetail> detail(@Path("id") long id);
}
