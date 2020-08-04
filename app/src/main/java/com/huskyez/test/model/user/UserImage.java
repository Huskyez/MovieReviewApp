package com.huskyez.test.model.user;

public class UserImage {

    private Avatar avatar;

    public UserImage() {
    }

    public UserImage(Avatar avatar) {
        this.avatar = avatar;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public static class Avatar {

        private String full;

        public Avatar() {
        }

        public Avatar(String full) {
            this.full = full;
        }

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }
    }
}
