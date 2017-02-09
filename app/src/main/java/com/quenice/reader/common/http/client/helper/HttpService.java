package com.quenice.reader.common.http.client.helper;

import com.dayxar.android.base.model.PushMessage;
import com.dayxar.android.base.scanner.model.LicenseMsg;
import com.dayxar.android.base.scanner.model.UploadImgReqParam;
import com.dayxar.android.common.banner.model.Banner;
import com.dayxar.android.common.http.model.CarInfo;
import com.dayxar.android.common.http.model.ErrorInfo;
import com.dayxar.android.common.http.model.ModuleVersion;
import com.dayxar.android.common.http.model.PageRequest;
import com.dayxar.android.common.http.model.PageResponse;
import com.dayxar.android.common.http.model.Resp;
import com.dayxar.android.common.http.model.ShortenUrl;
import com.dayxar.android.common.http.model.TokenInfo;
import com.dayxar.android.common.http.model.UserDetailInfo;
import com.dayxar.android.common.updater.ApkUpdateInfo;
import com.dayxar.android.common.util.Constants;
import com.dayxar.android.controller.model.CarStateModel;
import com.dayxar.android.home.base.model.LimitRule;
import com.dayxar.android.home.base.model.MessageType;
import com.dayxar.android.home.base.model.TotalIncome;
import com.dayxar.android.home.carcondition.model.CarConditionStatistic;
import com.dayxar.android.home.carcondition.model.HealthFator;
import com.dayxar.android.home.carcondition.model.InspectionResult;
import com.dayxar.android.home.challenge.model.ChallengeInfo;
import com.dayxar.android.home.challenge.model.ChallengeLevel;
import com.dayxar.android.home.discount.model.InvitationResp;
import com.dayxar.android.home.income.model.IncomeDetailModel;
import com.dayxar.android.home.income.model.IncomeModel;
import com.dayxar.android.home.income.model.IncomeOtherModel;
import com.dayxar.android.home.income.model.IncomeStopModel;
import com.dayxar.android.home.income.model.MonthlyModel;
import com.dayxar.android.home.map.model.CarLocation;
import com.dayxar.android.home.mileage.model.MileagePagination;
import com.dayxar.android.home.mileage.model.MileageResponse;
import com.dayxar.android.home.mileage.model.MileageStatisticsResponse;
import com.dayxar.android.home.notification.model.EmergencyContact;
import com.dayxar.android.home.statistic.model.StatisticModel;
import com.dayxar.android.insurance.model.ClaimModel;
import com.dayxar.android.insurance.model.ClaimProcess;
import com.dayxar.android.insurance.model.InsuranceCar;
import com.dayxar.android.insurance.model.InsuranceType;
import com.dayxar.android.person.account.model.LoginResp;
import com.dayxar.android.person.account.model.UserReq;
import com.dayxar.android.person.base.model.Feedback;
import com.dayxar.android.person.base.model.FeedbackTypes;
import com.dayxar.android.person.base.model.OSInfo;
import com.dayxar.android.person.base.model.UserAccountInfo;
import com.dayxar.android.person.bind.model.BindAuthInfo;
import com.dayxar.android.person.bind.model.BindCarInfoModel;
import com.dayxar.android.person.bind.model.BindData;
import com.dayxar.android.person.bind.model.CarBrand;
import com.dayxar.android.person.bind.model.CarFuelGrade;
import com.dayxar.android.person.bind.model.CarModel;
import com.dayxar.android.person.bind.model.CarSeries;
import com.dayxar.android.person.bind.model.EquBTInfo;
import com.dayxar.android.person.coupon.model.Coupon;
import com.dayxar.android.person.point.model.PointDetail;
import com.dayxar.android.person.share.model.InvitationInfo;
import com.dayxar.android.person.share.model.SharingInfo;
import com.dayxar.android.person.wallet.model.BalanceDetail;
import com.dayxar.android.person.wallet.model.PayAccountInfo;
import com.dayxar.android.person.wallet.model.WealthInfo;
import com.dayxar.android.person.wallet.model.WithdrawRecordModel;
import com.dayxar.android.weather.model.Weather;
import com.dayxar.android.weather.model.WeatherDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by qiubb on 2016/10/27.
 */
public interface HttpService {
	/**
	 * 是否需要token信息.默认true
	 */
	String HEADER_NEEDTOKEN = "Need-Token";
	/**
	 * 是否需要用标准格式包装参数.默认true
	 */
	String HEADER_WRAPPED_PARAM = "Wrapped-Param";
	/**
	 * 是否需要token信息：false
	 */
	String HEADER_NEEDTOKEN_FALSE = "Need-Token:false";
	/**
	 * 是否需要用标准格式包装参数：false
	 */
	String HEADER_WRAPPED_PARAM_FALSE = "Wrapped-Param:false";

	/**
	 * 获得车辆信息
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_CAR_INFO)
	Observable<Resp<CarInfo>> getCarInfo(@Body Map<String, String> params);

	/**
	 * 设置默认车辆
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.SET_DEFAULT_CAR)
	Observable<Resp<String>> setDefaultCar(@Body Map<String, String> params);

	/**
	 * 获得已绑定车辆列表的基本信息
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_BINDED_CARLIST)
	Observable<Resp<List<CarInfo>>> getBindedCarListBaseInfo();

	/**
	 * 修改用户开关配置信息
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.MODIFY_ACCOUNTINFO)
	Observable<Resp<String>> setUserSwitchConfig(@Body Map<String, String> params);

	/**
	 * 获取设备的蓝牙信息
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_BT_INFO)
	Observable<Resp<EquBTInfo>> getBTInfo(@Body Map<String, String> params);

	/**
	 * 上传车辆体检结果至服务器并返回体检分数
	 *
	 * @param mRequstEntity
	 * @return
	 */
	@POST(Constants.Interface.UPLOAD_CAR_INSP)
	Observable<Resp<Map<String, String>>> uploadInspection(@Body InspectionResult mRequstEntity);

	/**
	 * 获得近12个月所有的收益
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_USER_REWARD_SUMOFMONTH)
	Observable<Resp<IncomeModel>> getAllMonthlyIncomeData(@Body Map<String, String> params);

	/**
	 * 获取每月所有收益
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_USER_REWARD_OFMONTH)
	Observable<Resp<MonthlyModel>> getMonthlyIncomeData(@Body Map<String, String> params);

	/**
	 * 获得优驾收益
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_USER_DRIVING_REWARDDETAIL)
	Observable<Resp<List<IncomeDetailModel>>> getDrivingDetailIncome(@Body Map<String, String> params);

	/**
	 * 停驶收益
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_LAYOFF_REWARD)
	Observable<Resp<IncomeStopModel>> getStopIncome(@Body Map<String, String> params);

	/**
	 * 获得其他收益
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_USER_REWARDDETAIL)
	Observable<Resp<List<IncomeOtherModel>>> getOtherDetailIncome(@Body Map<String, String> params);

	/**
	 * 获得用户的账户信息
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_USER_ALLASSETS)
	Observable<Resp<UserAccountInfo>> getUserAccountInfo();

	/**
	 * 获得用户财富信息
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_USER_WEALTH)
	Observable<Resp<WealthInfo>> getUserWealthInfo();

	/**
	 * 获得用户余额明细
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_USER_BILLDETAILS)
	Observable<Resp<PageResponse<BalanceDetail>>> getUserBillDetail(@Body PageRequest<String> params);

	/**
	 * 获得统计数据
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_DRIVING_STATISTIC)
	Observable<Resp<StatisticModel>> getDrivingStatistic(@Body Map<String, Object> params);

	/**
	 * 获取车况统计数据：电压、冷却
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_CARCONDITION_STATISTIC)
	Observable<Resp<List<CarConditionStatistic>>> getCarCondtionStatistic(@Body Map<String, Object> params);

	/**
	 * 获得车型详细信息
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_CARMODEL)
	Observable<Resp<ArrayList<CarModel>>> getCarModel(@Body Map<String, String> params);

	/**
	 * 获得用户积分详细列表
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_POINT_DETAILS)
	Observable<Resp<PageResponse<PointDetail>>> getUserPointDetails(@Body PageRequest<Integer> params);

	/**
	 * 查询分享相关信息
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_SHAREINFO)
	Observable<Resp<List<SharingInfo>>> getShareInfo(@Body Map<String, Object> params);

	/**
	 * 获得最新的体检结果
	 *
	 * @param guid
	 * @return
	 */
	@POST(Constants.Interface.GET_LATEST_INSPECTION_REPORT)
	Observable<Resp<InspectionResult>> getLatestInspectionReport(@Body String guid);

	/**
	 * 应用优惠码(邀请码)
	 *
	 * @param invitationCode
	 * @return
	 */
	@POST(Constants.Interface.APPLY_INVITATIONCODE)
	Observable<Resp<InvitationResp>> applyInvitationCode(@Body String invitationCode);

	/**
	 * 发送验证码
	 *
	 * @param phone
	 * @param type
	 * @return
	 */
	@Headers({HEADER_WRAPPED_PARAM_FALSE, HEADER_NEEDTOKEN_FALSE})
	@POST(Constants.Interface.SEND_VERCODE)
	Observable<Resp<String>> sendVerCode(@Query("phone") String phone, @Query("smsCodeType") int type);

	/**
	 * 获得挑战基本信息
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_CHALLENGE_INFO)
	Observable<Resp<List<ChallengeInfo>>> getChallengeInfo(@Body Map<String, String> params);

	/**
	 * 获得近12个月挑战信息
	 *
	 * @param guid
	 * @return
	 */
	@POST(Constants.Interface.GET_MONTHLY_CHALLENGE_INFO)
	Observable<Resp<List<ChallengeInfo>>> getMonthlyChallengeInfo(@Body String guid);

	/**
	 * 根据type获得描述信息
	 *
	 * @param descType
	 * @return
	 */
	@POST(Constants.Interface.GET_DESC_INFO)
	Observable<Resp<Map<String, String>>> getDescInfo(@Body String descType);

	/**
	 * 获取能够设定的挑战等级
	 *
	 * @param guid
	 * @return
	 */
	@POST(Constants.Interface.GET_CHALLENGE_LEVELS)
	Observable<Resp<List<ChallengeLevel>>> getChallengeLevels(@Body String guid);

	/**
	 * 更新设定挑战信息
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.UPDATE_CHALLENGE_INFO)
	Observable<Resp<String>> updateChallengeInfo(@Body Map<String, Object> params);

	/**
	 * 注销
	 *
	 * @param params
	 * @return
	 */
	@Headers({HEADER_WRAPPED_PARAM_FALSE, HEADER_NEEDTOKEN_FALSE})
	@POST(Constants.Interface.LOGOUT)
	Observable<Resp<String>> logout(@Body Map<String, Object> params);

	/**
	 * 删除行程
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.DELETE_DRIVING)
	Observable<Resp<String>> deleteDriving(@Body Map<String, Object> params);

	/**
	 * 获得用户基本信息
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_USERINFO)
	Observable<Resp<UserDetailInfo>> getUserInfo();

	/**
	 * 修改紧急联系人信息
	 *
	 * @param emergencyContact
	 * @return
	 */
	@POST(Constants.Interface.UPDATE_EMERGENCY_INFO)
	Observable<Resp<String>> modifyEmergencyInfo(@Body EmergencyContact emergencyContact);

	/**
	 * 获得健康因素
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_CARHEALTHFATORS)
	Observable<Resp<List<HealthFator>>> getCarHealthFators();

	/**
	 * 购保-上传证件
	 *
	 * @param param
	 * @return
	 */
	@POST(Constants.Interface.SAVE_USER_CERTIFICATES)
	Observable<Resp<String>> saveUserCertificates(@Body UploadImgReqParam param);

	/**
	 * 获得用户总收益
	 *
	 * @param param
	 * @return
	 */
	@POST(Constants.Interface.GET_USER_SUMREWARD)
	Observable<Resp<TotalIncome>> getUserSumReward(@Body Map<String, String> param);

	/**
	 * 获得账户余额
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_BALANCE)
	Observable<Resp<Float>> getBalance();

	/**
	 * 获取支付账号信息
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_PAY_ACCOUNT_INFO)
	Observable<Resp<List<PayAccountInfo>>> getPayAccountInfo();

	/**
	 * 绑定提现帐户
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.SAVE_PAY_ACCOUNT_INFO)
	Observable<Resp<String>> savePayAccountInfo(@Body Map<String, String> map);

	/**
	 * 解绑提现帐户
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.UNBIND_PAY_ACCOUNT)
	Observable<Resp<String>> unbindPayAccount(@Body Map<String, String> map);

	/**
	 * 提现
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.WITHDRAW)
	Observable<Resp<String>> withdraw(@Body Map<String, String> map);

	/**
	 * 获得各个模块版本信息
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_MODULEVERSION)
	Observable<Resp<List<ModuleVersion>>> getModuleVersion();

	/**
	 * 提现明细
	 *
	 * @param pageRequest
	 * @return
	 */
	@POST(Constants.Interface.GET_WITHDRAW_BILLS)
	Observable<Resp<PageResponse<WithdrawRecordModel>>> getWithdrawBills(@Body PageRequest<Integer> pageRequest);

	/**
	 * 获得天气详情
	 *
	 * @param map
	 * @return
	 */
	@Headers({HEADER_WRAPPED_PARAM_FALSE, HEADER_NEEDTOKEN_FALSE})
	@POST(Constants.Interface.WEATHER_DETAIL)
	Observable<Resp<WeatherDetail>> getWeatherDetail(@Body Map<String, String> map);

	/**
	 * 获得天气信息
	 *
	 * @param map
	 * @return
	 */
	@Headers({HEADER_WRAPPED_PARAM_FALSE, HEADER_NEEDTOKEN_FALSE})
	@POST(Constants.Interface.WEATHER)
	Observable<Resp<Weather>> getWeather(@Body Map<String, String> map);

	/**
	 * 转换短链接
	 *
	 * @param urls
	 * @return
	 */
	@Headers({HEADER_WRAPPED_PARAM_FALSE, HEADER_NEEDTOKEN_FALSE})
	@GET("http://api.t.sina.com.cn/short_url/shorten.json?source=1268393953")
	Observable<List<ShortenUrl>> getShortenUrl(@Query("url_long") String... urls);

	/**
	 * 获得基本邀请信息
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_USER_INVITATION)
	Observable<Resp<InvitationInfo>> getBaseInvitationInfo();

	/**
	 * 获得优惠券列表（分页）
	 *
	 * @param pageRequest
	 * @return
	 */
	@POST(Constants.Interface.GET_USER_COUPON_LIST)
	Observable<Resp<PageResponse<Coupon>>> getCouponList(@Body PageRequest<String> pageRequest);

	/**
	 * 翻译百度坐标为地址信息
	 *
	 * @param output
	 * @param ak
	 * @param location
	 * @return
	 */
	@Headers({HEADER_WRAPPED_PARAM_FALSE, HEADER_NEEDTOKEN_FALSE})
	@GET("http://api.map.baidu.com/geocoder/v2/")
	Observable<Map<String, Object>> translateBaiduPosition(@Query("output") String output, @Query("ak") String ak, @Query("location") String location);

	/**
	 * 获得车辆镜像数据
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.GET_CAR_SNAPSHOT)
	Observable<Resp<Map<String, String>>> getCarSnapshot(@Body Map<String, String> map);

	/**
	 * 获得实时车辆状态数据
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.GET_CAR_STATE)
	Observable<Resp<CarStateModel>> getRealTimeCarState(@Body Map<String, String> map);

	/**
	 * 获得车辆控制配置
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.CAR_CONTROL_CMD)
	Observable<Resp<Map<String, Integer>>> getCarCMDConfig(@Body Map<String, String> map);

	/**
	 * 获得详细行驶轨迹
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.GET_DRIVE_TRACK)
	Observable<Resp<MileageResponse>> getMileageDetail(@Body Map<String, String> map);

	/**
	 * 判断给定点是否高危点
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.IS_HIGH_RISK_POINT)
	Observable<Resp<Map<String, Integer>>> isHighRiskPoint(@Body Map<String, Double> map);

	/**
	 * 更新车辆隐私设置
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.UPDATE_PRIVACYSTATUS)
	Observable<Resp<String>> updatePrivacyStatus(@Body Map<String, Object> map);

	/**
	 * 密码验证
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.VERIFY_PASSWORD)
	Observable<Resp<String>> verifyPassword(@Body Map<String, Object> map);

	/**
	 * 获得已设置的碰撞等级
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.GET_CAR_COLLISION_LEVEL)
	Observable<Resp<Map<String, String>>> getCarCollisionLevel(@Body Map<String, String> map);

	/**
	 * 获得车辆列表
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_CARINFO_LIST)
	Observable<Resp<List<CarInfo>>> getCarList();

	/**
	 * 获得车辆列表（绑定专用）
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_CARLIST_4BIND)
	Observable<Resp<BindCarInfoModel>> getCarList4Bind();

	/**
	 * 新增车辆
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.ADD_CARINFO)
	Observable<Resp<String>> addCar(@Body Map<String, Object> params);

	/**
	 * 获得默认车辆及车辆数量
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_DEFAULT_CARINFO)
	Observable<Resp<CarInfo>> getDefaultCarAndCarNum();

	/**
	 * 删除车辆
	 *
	 * @param carId
	 * @return
	 */
	@POST(Constants.Interface.DELETE_CAR)
	Observable<Resp<String>> deleteCar(@Body String carId);

	/**
	 * 获取一周的行驶列表
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_MILEAGELIST_BYWEEK)
	Observable<Resp<MileagePagination>> getMileageListByWeek(@Body Map<String, Object> params);

	/**
	 * 获得行驶统计数据
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_MILEAGE_STATISTIC)
	Observable<Resp<MileageStatisticsResponse>> getMileageStatisticList(@Body Map<String, Object> params);

	/**
	 * 获得报险类型
	 *
	 * @param codeType
	 * @return
	 */
	@POST(Constants.Interface.GET_CLAIM_NOTIFYTYPE)
	Observable<Resp<Map<String, List<InsuranceType>>>> getInsuranceClaimType(@Body String codeType);

	/**
	 * 获得保险车辆列表
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_INS_CARLIST)
	Observable<Resp<Map<String, List<InsuranceCar>>>> getInsuranceCarList();

	/**
	 * 提交报险信息
	 *
	 * @param claimModel
	 * @return
	 */
	@POST(Constants.Interface.SAVE_INSURANCE_CLAIM)
	Observable<Resp<ClaimModel>> saveInsuranceClaim(@Body ClaimModel claimModel);

	/**
	 * 获得报险列表
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_CLAIM_LIST)
	Observable<Resp<List<ClaimModel>>> getInsuranceClaimList();

	/**
	 * 获得报险处理流程列表
	 *
	 * @param m
	 * @return
	 */
	@POST(Constants.Interface.GET_CLAIM_PROCESS_LIST)
	Observable<Resp<List<ClaimProcess>>> getInsuranceClaimProcessList(@Body Map<String, String> m);

	/**
	 * 获取消息列表
	 *
	 * @param pageRequest
	 * @return
	 */
	@POST(Constants.Interface.ACCOUNT_USER_GETMSGBYPAGING)
	Observable<Resp<PageResponse<PushMessage>>> getMessageList(@Body PageRequest<Integer> pageRequest);

	/**
	 * 批量更新消息状态
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.UPDATE_MSG_STATUS_BATH)
	Observable<Resp<String>> updateMessageStatus(@Body Map<String, Object> map);

	/**
	 * 基于横幅类型查询横幅栏目信息
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.GET_BANNERINFO)
	Observable<Resp<List<Banner>>> getBannerInfos(@Body Map<String, Object> map);

	/**
	 * 获得服务器上新版本信息
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.GET_APK_VERSIONINFO)
	Observable<Resp<ApkUpdateInfo>> getApkVersionInfo(@Body Map<String, Object> map);

	/**
	 * 上传错误信息
	 *
	 * @param errorInfo
	 * @return
	 */
	@POST(Constants.Interface.UPLOAD_ERROR_INFO)
	Observable<Resp<String>> uploadErrorInfo(@Body ErrorInfo errorInfo);

	/**
	 * 注册
	 *
	 * @param params
	 * @return
	 */
	@Headers({HEADER_NEEDTOKEN_FALSE, HEADER_WRAPPED_PARAM_FALSE})
	@POST(Constants.Interface.REGISTER)
	Observable<Resp<String>> regist(@Body UserReq params);

	/**
	 * 上传图片
	 *
	 * @param type
	 * @param userAccount
	 * @param loginData
	 * @param file
	 * @return
	 */
	@Headers({HEADER_NEEDTOKEN_FALSE, HEADER_WRAPPED_PARAM_FALSE})
	@Multipart
	@POST(Constants.Interface.UPLOAD_HEADIMG)
	Observable<Resp<String>> uploadUserPhoto(@Part("tokenInfo.type") RequestBody type, @Part("tokenInfo.userAccount") RequestBody userAccount, @Part("tokenInfo.loginData") RequestBody loginData, @Part MultipartBody.Part file);

	/**
	 * 上传证件照片
	 *
	 * @param type
	 * @param userAccount
	 * @param loginData
	 * @param file
	 * @return
	 */
	@Headers({HEADER_NEEDTOKEN_FALSE, HEADER_WRAPPED_PARAM_FALSE})
	@Multipart
	@POST(Constants.Interface.UPLOAD_LICENSE_IMG)
	Observable<Resp<LicenseMsg>> uploadLicenseImg(@Part("tokenInfo.type") RequestBody type, @Part("tokenInfo.userAccount") RequestBody userAccount, @Part("tokenInfo.loginData") RequestBody loginData, @Part MultipartBody.Part file);

	/**
	 * 上传图片
	 *
	 * @param type
	 * @param userAccount
	 * @param loginData
	 * @param file
	 * @return
	 */
	@Headers({HEADER_NEEDTOKEN_FALSE, HEADER_WRAPPED_PARAM_FALSE})
	@Multipart
	@POST(Constants.Interface.UPLOAD_IMG)
	Observable<Resp<String>> uploadImg(@Part("tokenInfo.type") RequestBody type, @Part("tokenInfo.userAccount") RequestBody userAccount, @Part("tokenInfo.loginData") RequestBody loginData, @Part MultipartBody.Part file);

	/**
	 * 检测登陆状态
	 *
	 * @param tokenInfo
	 * @return
	 */
	@Headers({HEADER_NEEDTOKEN_FALSE, HEADER_WRAPPED_PARAM_FALSE})
	@POST(Constants.Interface.CHECK_LOGINSTATUS)
	Observable<Resp<String>> checkLoginStatus(@Body TokenInfo tokenInfo);

	/**
	 * 限行规则（无权限验证）
	 *
	 * @param map
	 * @return
	 */
	@Headers({HEADER_NEEDTOKEN_FALSE, HEADER_WRAPPED_PARAM_FALSE})
	@POST(Constants.Interface.GET_LIMITINFO_NO_AUTH)
	Observable<Resp<LimitRule>> getLimitInfoNoAuth(@Body Map<String, String> map);

	/**
	 * 获得消息总条数
	 *
	 * @param type
	 * @return
	 */
	@POST(Constants.Interface.GET_MSG_COUNTS)
	Observable<Resp<String>> getMsgCount(@Body String type);

	/**
	 * 获得车辆坐标
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.GET_CARLOCATION)
	Observable<Resp<CarLocation>> getCarLocation(@Body Map<String, String> map);

	/**
	 * 修改账号信息
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.MODIFY_ACCOUNTINFO)
	Observable<Resp<String>> modifyAccountInfo(@Body Map<String, String> map);

	/**
	 * 修改密码
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.CHANGE_PASSWORD)
	Observable<Resp<String>> changePassword(@Body Map<String, Object> map);

	/**
	 * 重置密码
	 *
	 * @param map
	 * @return
	 */
	@Headers({HEADER_NEEDTOKEN_FALSE, HEADER_WRAPPED_PARAM_FALSE})
	@POST(Constants.Interface.RESET_PASSWORD)
	Observable<Resp<String>> resetPassword(@Body Map<String, Object> map);

	/**
	 * 登陆
	 *
	 * @param userReq
	 * @return
	 */
	@Headers({HEADER_NEEDTOKEN_FALSE, HEADER_WRAPPED_PARAM_FALSE})
	@POST(Constants.Interface.LOGIN)
	Observable<Resp<LoginResp>> login(@Body UserReq userReq);

	/**
	 * 快捷登陆
	 *
	 * @param userReq
	 * @return
	 */
	@Headers({HEADER_NEEDTOKEN_FALSE, HEADER_WRAPPED_PARAM_FALSE})
	@POST(Constants.Interface.QUICK_LOGIN)
	Observable<Resp<LoginResp>> quickLogin(@Body UserReq userReq);

	/**
	 * 反馈
	 *
	 * @param feedback
	 * @return
	 */
	@POST(Constants.Interface.UP_FEEDBACK)
	Observable<Resp<String>> doFeedback(@Body Feedback feedback);

	/**
	 * 手机应用获取设备升级版本信息
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.GET_DEVICE_VERSION)
	Observable<Resp<OSInfo>> getDeviceVesion(@Body Map<String, String> map);

	/**
	 * 设备升级时，发送信息至后台校验
	 *
	 * @param map
	 * @return
	 */
	@POST(Constants.Interface.BIND_EQUEPMENT)
	Observable<Resp<BindAuthInfo>> sendInfoWhenUpgrade(@Body Map<String, String> map);

	/**
	 * 获得反馈类型列表
	 *
	 * @return
	 */
	@Headers({HEADER_NEEDTOKEN_FALSE, HEADER_WRAPPED_PARAM_FALSE})
	@POST(Constants.Interface.GET_FEEDBACK_TYPE)
	Observable<Resp<FeedbackTypes>> getFeedbackType();

	/**
	 * 设备绑定
	 *
	 * @param bindData
	 * @return
	 */
	@POST(Constants.Interface.HAND_BIND_INFO)
	Observable<Resp<Map<String, String>>> doBind(@Body BindData bindData);

	/**
	 * 获得油耗等级信息
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_FUELGRADE)
	Observable<Resp<ArrayList<CarFuelGrade>>> getFuelGrade();

	/**
	 * 获得车型列表
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_CAR_BRANDS)
	Observable<Resp<CarBrand>> getCarBrands();

	/**
	 * 获取品牌下子品牌-车系
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.GET_SUBBRAND_SERIES_BYBRAND)
	Observable<Resp<List<CarSeries>>> getSubbrandsAndSeriesByBrand(@Body Map<String, String> params);

	/**
	 * 更新车辆基本信息
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.UPDATE_CAR_BASE_INFO)
	Observable<Resp<String>> updateCarBaseInfo(@Body Map<String, Object> params);

	/**
	 * 更新车辆身份信息
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.UPDATE_CAR_CORE_INFO)
	Observable<Resp<String>> updateCarIDInfo(@Body Map<String, Object> params);

	/**
	 * 上传app信息
	 *
	 * @param params
	 * @return
	 */
	@Headers({HEADER_NEEDTOKEN_FALSE, HEADER_WRAPPED_PARAM_FALSE})
	@POST(Constants.Interface.UPLOAD_APPINFO)
	Observable<Resp<String>> uploadAppInfo(@Body Map<String, Object> params);

	/**
	 * 更新消息状态
	 *
	 * @param params
	 * @return
	 */
	@POST(Constants.Interface.UPDATE_MESSAGE_STATUS)
	Observable<Resp<String>> updateMsgStatus(@Body Map<String, Object> params);

	/**
	 * 获得最新的分类消息跟数量
	 *
	 * @return
	 */
	@POST(Constants.Interface.GET_LASTEST_CAT_MSG)
	Observable<Resp<List<MessageType>>> getLatestCatMsg();

	/**
	 * 获得动态模块的信息
	 * @return
	 */
	@POST(Constants.Interface.GET_DYNAMIC_MODULE_INFO)
	Observable<Resp<List<Banner>>> getDynamicModuleInfo();
}
