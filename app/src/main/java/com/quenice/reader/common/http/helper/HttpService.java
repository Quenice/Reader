package com.quenice.reader.common.http.helper;

import com.quenice.reader.detail.model.ZhihuDailyDetail;
import com.quenice.reader.main.model.ZhihuDaily;
import com.quenice.reader.common.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
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
	@GET(Constants.Urls.ZhiHuDaily.LIST_LATEST)
	Observable<ZhihuDaily> zhihuDailyListLatest();

	@GET(Constants.Urls.ZhiHuDaily.LIST_BEFORE + "/{date}")
	Observable<ZhihuDaily> zhihuDailyListBefore(@Path("date") String date);
	@GET(Constants.Urls.ZhiHuDaily.DETAIL + "/{id}")
	Observable<ZhihuDailyDetail> zhihuDailyDetail(@Path("id") long id);
}
