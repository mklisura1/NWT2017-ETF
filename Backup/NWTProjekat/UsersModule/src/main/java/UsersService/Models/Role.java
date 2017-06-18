package UsersService.Models;

public enum Role {
    ADMIN, USER, PREMIUM_MEMBER, MEMBER;
    
    public String authority() {
        return "ROLE_" + this.name();
    }
}
