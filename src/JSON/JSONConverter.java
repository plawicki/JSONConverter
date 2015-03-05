package JSON;
 
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public interface JSONConverter {
	
	public default String toJSON()
	{
		final StringBuilder retVal = new StringBuilder();
		try {
			final Field[] fields = this.getClass().getDeclaredFields();
			
			retVal.append("{\n").append("\t\"name\":\"").append(this.getClass().getName()).append("\",");
			for(Field i : fields)
			{
				i.setAccessible(true);
				
				retVal.append("\n\t\"").append(i.getName()).append("\":");
				
				if(i.getType().isArray())
				{
					retVal.append("[");
					final Object array = i.get(this);
					final int length = Array.getLength(array);
					for(int j=0; j<length; j++)
					{
						retVal.append("\"").append(Array.get(array, j)).append("\",");
					}
					retVal.deleteCharAt(retVal.length()-1);
					retVal.append("]");
				}
				else if(i.getType().getPackage() != null && !i.getType().getPackage().getName().startsWith("java") && JSONConverter.class.isAssignableFrom(i.get(this).getClass()))
				{
					retVal.append(i.get(this).getClass().getMethod("toJSON", null).invoke(i.get(this), null));
				}
				else
				{
					String fieldStr = i.get(this).toString();
					if(fieldStr.contains("[") || fieldStr.contains("]"))
					{
						retVal.append("[");
						for(String s : fieldStr.split(", "))
						{
							retVal.append("\"").append(s.replace("[", "").replace("]", "")).append("\",");
						}
						retVal.deleteCharAt(retVal.length()-1);
						retVal.append("]");
					}
					else
					{
						retVal.append("\"").append(i.get(this)).append("\"");
					}
				}
				retVal.append(",");
			}
			retVal.deleteCharAt(retVal.length()-1);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		retVal.append("\n}");
		
		return retVal.toString();
	}
}
