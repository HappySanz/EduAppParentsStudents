package com.palprotech.eduappparentsstudents.customview.customcalendarsupport;


import com.palprotech.eduappparentsstudents.customview.customcalendar.CaldroidFragment;
import com.palprotech.eduappparentsstudents.customview.customcalendar.CaldroidGridAdapter;

public class CaldroidSampleCustomFragment extends CaldroidFragment {

	@Override
	public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
		// TODO Auto-generated method stub
		return new CaldroidSampleCustomAdapter(getActivity(), month, year,
				getCaldroidData(), extraData);
	}

}
