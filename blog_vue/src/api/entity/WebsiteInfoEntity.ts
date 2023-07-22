declare type WebsiteInfoEntity = {
  articleCount: number,
  categoryCount: number,
  tagCount: number,
  viewsCount: number,
  onlyViewCount: number,
  websiteAvatar: string,
  websiteName: string,
  websiteAuthor: string,
  websiteIntro: string,
  websiteNotice: string,
  websiteCreateTime: string,
  websiteRecordNo: string,
  websiteBackgroundImages: Array<string>,
  homeTyperTexts: Array<string>
  socialLogin: {},
  qq: string,
  wechat: string,
  touristAvatar: string,
  defaultAvatar: string,
  weiXinQRCode: string,
  alipayQRCode: string,
  isCommentReview: string,
  isMessageReview: string,
  isAutoPlayer: boolean,
  chatRoomNotice: string,
}