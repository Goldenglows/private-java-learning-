public class merget2d {

    private static Comparable[] aux;

    private static void sort(Comparable[] a)
    {
        //分配空间
        aux = new Comparable[a.length];
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a,int lo,int hi)
    {
        if(hi <= lo)
            return;

        int mid = lo + (hi - lo)/2;
        //先依次排序两个一半，然后再将分开的两个数组原地归并成一个
        //每次都会调用，每次调用都会把现在的一个分成两个一半，次取其半，直到为1
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);

    }


    public static void merge(Comparable[] a, int lo, int mid, int hi)
    {
        //两个数组，lo mid  mid+1 hi进行归并
        int i = lo, j = mid + 1;
        //将需要操作的数组复制到新的数组中
        for(int k = lo; k <= hi; k++)
        {
            aux[k] = a[k];
        }

        for(int k = lo; k <= hi; k++)
        {
            //3 如果左边的数组全部进入原来的大数组中，则后面直接把右边的数组全部导入即可
            if(i > mid)
                a[k] = aux[j++];
                //4 相对的如果右边全进，则把左边全部导入
            else if (j > hi)
                a[k] = aux[i++];
                //判断左右两个数组的第一个哪个小
                //1 右边小就讲原来的大数组第一个开始赋值为两边最小的值
            else if(less(aux[j], aux[i]))

                a[k] = aux[j++];
                //2 左边小就赋值给大数组，并且往左平移一个
            else
                a[k] = aux[i++];
        }
    }


    private static boolean less(Comparable v,Comparable w)
    {
        return v.compareTo(w)<0;
    }

    private static void exch(Comparable[] a,int i,int j)
    {
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }

    private static void show(Comparable[] a)
    {
        for(int i=0;i<a.length;i++)
            StdOut.print(a[i]+"");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a)
    {
        for(int i=1;i<a.length;i++)
            if(less(a[i],a[i-1]))
                return false;
        return true;
    }


}
