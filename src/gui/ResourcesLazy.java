package gui;

public class ResourcesLazy {
	
	
	private static class LazyHolder{
		private static final Resources res = new Resources();
		
	}
	
	public static Resources getRes() {
		return LazyHolder.res;
	}
	
}
