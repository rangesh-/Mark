package com.mark.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MarkerDragHandler;
import com.google.gwt.maps.client.event.MarkerDragHandler.MarkerDragEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.gwtphonegap.client.geolocation.Coordinates;
import com.googlecode.gwtphonegap.client.geolocation.GeolocationCallback;
import com.googlecode.gwtphonegap.client.geolocation.GeolocationOptions;
import com.googlecode.gwtphonegap.client.geolocation.GeolocationWatcher;
import com.googlecode.gwtphonegap.client.geolocation.Position;
import com.googlecode.gwtphonegap.client.geolocation.PositionError;
import com.googlecode.gwtphonegap.client.notification.AlertCallback;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;

public class index{

	public void onModuleLoad() {
		// TODO Auto-generated method stub
final PhoneGap gap=GWT.create(PhoneGap.class);
final Button but=new Button("Home");
gap.addHandler(new PhoneGapAvailableHandler() {
	
	@Override
	public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
		// TODO Auto-generated method stub
		RootPanel.get().add(but);
		
		GeolocationOptions options = new GeolocationOptions();
		options.setFrequency(100);
		
		 GeolocationWatcher watch=gap.getGeolocation().watchPosition(options, new GeolocationCallback() {
			
			@Override
			public void onSuccess(Position position) {
				// TODO Auto-generated method stub
			Window.alert(""+position.getCoordinates());
			Coordinates pos=position.getCoordinates();
			final double lats=pos.getLatitude();
			final double longs=pos.getLongitude();
			System.out.println(pos.getLatitude());
			System.out.println(pos.getLongitude());
			System.out.println(pos.getSpeed());
			
			 Maps.loadMapsApi("", "2", false, new Runnable() {
			      public void run() {
			        buildUi();
			      }

				private void buildUi() {
					// TODO Auto-generated method stub
				final LatLng locate= LatLng.newInstance(lats, longs);	
				final MapWidget maps=new MapWidget(locate, 2);
			
				maps.addControl(new LargeMapControl());
			LatLng[] points= new LatLng[2] ;
			points[0]=LatLng.newInstance(13.060416, 79.249634);
			points[1]=LatLng.newInstance(13.060416, 78.249634);
			Polyline pol=new Polyline(points);
			pol.setVisible(true);
		Marker mark=new Marker(locate);
		
			pol.setEditingEnabled(true);
			maps.addOverlay(pol);
			maps.addOverlay(mark);
				maps.setZoomLevel(16);
				FlowPanel panel=new FlowPanel();
				maps.setSize("400px", "400px");
				panel.setSize("600px", "600px");
				panel.add(maps);
				RootPanel.get().add(panel);
				RootPanel.get().add(but);
				but.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						Marker home=new Marker(locate);
					}
				});
			
				}
			    });
			}
			
			@Override
			public void onFailure(PositionError error) {
				// TODO Auto-generated method stub
				
			}
		});
	}
});

gap.addHandler(new PhoneGapTimeoutHandler() {
	
	@Override
	public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
		// TODO Auto-generated method stub
		
	}
});

gap.initializePhoneGap();
	}

}
