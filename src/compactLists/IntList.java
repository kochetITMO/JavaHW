package compactLists;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class IntList {
	int[] list;
	int size;

	public IntList() {
		this(4);
	}

	public IntList(int capacity) {
		this.list = new int[capacity];
		this.size = 0;
	}

	public void add(int x) {
		if (size == list.length) {
			list = Arrays.copyOf(list, size * 2);
		}
		list[size++] = x;
	}

	public int get(int index) {
		return list[index];
	}

	public int getSize() {
		return size;
	}

	public void print(Writer out, String sep, int start, int end) throws IOException {
		for (int i = start; i < end - 1; i++) {
			out.write(Integer.toString(list[i]));
			out.write(sep);
		}
		out.write(Integer.toString(list[size - 1]));
	}

	public void print(Writer out) throws IOException {
		print(out, " ", 0, size);
	}
	
	interface Foreach {
		void foo(int x);
	}
	
	public void forEach(Foreach f) {
		for (int i = 0; i < size; i++) {
			f.foo(list[i]);
		}
	}
}