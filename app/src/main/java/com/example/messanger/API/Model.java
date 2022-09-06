package com.example.messanger.API;

import java.util.ArrayList;

public class Model {
    String page;
    String per_page;
    String total;
    String total_pages;
    ArrayList<data> data;

    public Model() {
        data = new ArrayList<data>();
    }

    public String getPage() {
        return page;
    }
    public void setPage(String page) {
        this.page = page;
    }

    public String getPer_page() {
        return per_page;
    }
    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_pages() {
        return total_pages;
    }
    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<data> getData() {
        return data;
    }
    public void setData(ArrayList<data> data) {
        this.data = data;
    }

    public class data {
        String id;
        String email;
        String first_name;
        String last_name;
        String avatar;

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirst_name() {
            return first_name;
        }
        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }
        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getAvatar() {
            return avatar;
        }
        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
