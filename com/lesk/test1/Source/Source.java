package com.lesk.test1.Source;

public class Source {
	private String sourceDescr;
	private int priority; // 1-min, 10-max

	public Source(String sourceDescr, int priority) {
		this.sourceDescr = sourceDescr;
		this.priority = priority;
	}
        
	public String getSourceDescr()  {return sourceDescr;}
	public int getPriority() 		{return priority;	}
        
	public void setSourceDescr(String sourceDescr)  {this.sourceDescr = sourceDescr;}
	public void setPriority(int priority)           {this.priority = priority;      }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + priority;
		result = prime * result + ((sourceDescr == null) ? 0 : sourceDescr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Source other = (Source) obj;
		if (priority != other.priority)
			return false;
		if (sourceDescr == null) {
			if (other.sourceDescr != null)
				return false;
		} else if (!sourceDescr.equals(other.sourceDescr))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Source [sourceDescr=" + sourceDescr + ", priority=" + priority + "]";
	}
}
