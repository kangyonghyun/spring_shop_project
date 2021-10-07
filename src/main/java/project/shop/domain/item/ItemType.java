package project.shop.domain.item;


public enum ItemType {

    ELECTRONIC("전자"), PAPER("종이"), ETC("기타");

    private String description;

    ItemType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

}
