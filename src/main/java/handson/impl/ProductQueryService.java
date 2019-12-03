package handson.impl;

import io.sphere.sdk.categories.Category;
import io.sphere.sdk.categories.queries.CategoryQuery;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.products.queries.ProductProjectionQuery;
import io.sphere.sdk.products.queries.ProductQuery;
import io.sphere.sdk.queries.PagedQueryResult;
import io.sphere.sdk.queries.PagedResult;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * This class provides query operations for {@link ProductProjection}s.
 */
public class ProductQueryService extends AbstractService {

    public ProductQueryService(SphereClient client) {
        super(client);
    }

    /**
     * @param locale
     * @param name
     * @return
     */
    private CompletionStage<PagedQueryResult<Category>> findCategory(final Locale locale, final String name) {
        // TODO Task16.1 Find a category

        return client.execute(CategoryQuery.of().byName(locale, name));
    }

    /**
     * Queries product projections that belong to given category
     *
     * @param category
     * @return Paged result of Product projections
     */
    private CompletionStage<PagedQueryResult<ProductProjection>> withCategory(final Category category) {
        // TODO Task16.2 Query a category

        ProductProjectionQuery productProjectionQuery = ProductProjectionQuery.ofCurrent().withPredicates(productProjectionQueryModel ->
                productProjectionQueryModel.categories()
                        .isIn(Collections.singletonList(category)));
        return client.execute(productProjectionQuery);

    }

    /**
     * Finds products with categories that have the given localized name.
     *
     * @param locale the locale
     * @param name   the localized name
     * @return the product query completion stage
     */
    public CompletionStage<PagedQueryResult<ProductProjection>> findProductsWithCategory(final Locale locale, final String name) {
        // TODO 16.3 Find a product with category
        Category category = findCategory(locale,name).toCompletableFuture().join().getResults().get(0);
        return withCategory(category);
    }
}
