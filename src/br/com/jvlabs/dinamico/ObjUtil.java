package br.com.jvlabs.dinamico;

public class ObjUtil<T, U> {

	private T util1;
	private U util2;
	private Object util3;
	private Object util4;
	private Object util5;

	public ObjUtil(T util1) {
		super();
		this.util1 = util1;
	}

	public ObjUtil(T util1, U util2) {
		super();
		this.util1 = util1;
		this.util2 = util2;
	}

	public ObjUtil(T util1, U util2, Object util3) {
		super();
		this.util1 = util1;
		this.util2 = util2;
		this.util3 = util3;
	}

	public ObjUtil(T util1, U util2, Object util3, Object util4) {
		super();
		this.util1 = util1;
		this.util2 = util2;
		this.util3 = util3;
		this.util4 = util4;
	}

	public ObjUtil(T util1, U util2, Object util3, Object util4, Object util5) {
		super();
		this.util1 = util1;
		this.util2 = util2;
		this.util3 = util3;
		this.util4 = util4;
		this.util5 = util5;
	}

	public ObjUtil() {
	}

	public T getUtil1() {
		return util1;
	}

	public void setUtil1(T util1) {
		this.util1 = util1;
	}

	public U getUtil2() {
		return util2;
	}

	public void setUtil2(U util2) {
		this.util2 = util2;
	}

	public Object getUtil3() {
		return util3;
	}

	public void setUtil3(Object util3) {
		this.util3 = util3;
	}

	public Object getUtil4() {
		return util4;
	}

	public void setUtil4(Object util4) {
		this.util4 = util4;
	}

	public Object getUtil5() {
		return util5;
	}

	public void setUtil5(Object util5) {
		this.util5 = util5;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((util1 == null) ? 0 : util1.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjUtil other = (ObjUtil) obj;
		if (util1 == null) {
			if (other.util1 != null)
				return false;
		} else if (!util1.equals(other.util1))
			return false;
		return true;
	}
}
