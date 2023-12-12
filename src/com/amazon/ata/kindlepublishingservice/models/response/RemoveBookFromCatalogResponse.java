package com.amazon.ata.kindlepublishingservice.models.response;

import java.util.Objects;

public class RemoveBookFromCatalogResponse {
    private Boolean removed;

    public RemoveBookFromCatalogResponse(boolean removed) {
        this.removed = removed;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RemoveBookFromCatalogResponse)) return false;
        RemoveBookFromCatalogResponse that = (RemoveBookFromCatalogResponse) o;
        return Objects.equals(getRemoved(), that.getRemoved());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRemoved());
    }

    public RemoveBookFromCatalogResponse(Builder builder) {
        this.removed = builder.removed;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Boolean removed;

        private Builder() {

        }

        public Builder withRemoved(boolean removed) {
            this.removed = removed;
            return this;
        }

        public RemoveBookFromCatalogResponse build() {
            return new RemoveBookFromCatalogResponse(this);
        }
    }
}
