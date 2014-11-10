package com.siondream.freegemas;

public class Square  {
	public enum Type {sqEmpty,
					  sqWhite,
					  sqRed,
					  sqPurple,
					  sqOrange,
					  sqGreen,
					  sqYellow,
					  sqBlue};
	
	public int fallStartPosY;
	public int fallDistance;
	public boolean mustFall;
	private Type _type;
	
	public Square(Type type) {
		_type = type;
		mustFall = true;
	}
	
	public Square(Square other) {
		_type = other._type;
		fallStartPosY = other.fallStartPosY;
		fallDistance = other.fallDistance;
		mustFall = other.mustFall;
	}
	
	public Type getType() {
		return _type;
	}
	
	public void setType(Type type) {
		_type = type;
	}
	
	public boolean equals(Square other) {
		return other._type == _type;
	}
	
	public boolean equals(Type type) {
		return type == _type;
	}
	
	public static Type numToType(int num) {
		switch (num) {
		case 1:
			return Type.sqWhite;
		case 2:
			return Type.sqRed;
		case 3:
			return Type.sqPurple;
		case 4:
			return Type.sqOrange;
		case 5:
			return Type.sqGreen;
		case 6:
			return Type.sqYellow;
		case 7:
			return Type.sqBlue;
		default:
			return Type.sqEmpty;
		}
	}
	public String toString() {
		return this._type.toString();
	}
}
