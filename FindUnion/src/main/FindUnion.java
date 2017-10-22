package main;
public interface FindUnion<T> {

	public abstract void makeSet(T x);

	public abstract T find(T x);

	public abstract void union(T x, T y);

}