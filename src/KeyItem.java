public class KeyItem implements Comparable {

    String title; //Platform or whatever
    String account ; //Username or E-Mail
    String key; //Secret

    public KeyItem(String title, String account, String key) {
        this.title = title;
        this.account = account;
        this.key = key;
    }

    @Override
    public int compareTo(Object o) {
        return this.title.compareTo(((KeyItem)o).title);
    }
}
