package com.drzk.online.service;

import com.drzk.offline.vo.CenterUpdateVO;

/** 即时同步数据 */
public interface OnlineDSSever
{
	void parkcarin(CenterUpdateVO model);
	void parkcarout(CenterUpdateVO model);
	void boxinfo(CenterUpdateVO model);
	void controllerinfo(CenterUpdateVO model);
	void parkcameras(CenterUpdateVO model);
	void parkblackcar(CenterUpdateVO model);
	void parkunusualcarin(CenterUpdateVO model);
	void parkcaroperation(CenterUpdateVO model);
	
	
	
}
