package com.searchingsortingbigopartb.prework;

import java.util.List;

/**
 * Manages a list of AmazonPackages.
 * Individual packages can be found by ASIN.
 */
public class AmazonOrderService {

    private List<AmazonPackage> packages;

    /**
     * Constructs an AmazonOrderService object.
     * @param packages - The List of packages in the order
     */
    public AmazonOrderService(List<AmazonPackage> packages) {
        this.packages = packages;
    }

    /**
     * Does a linear search for a package in the known list of packages
     * @param asin - The ASIN being searched for.
     * @return the Amazon Package with the target ASIN
     */
    public AmazonPackage findPackageLinear(String asin) throws PackageNotFoundException {
        if (packages.isEmpty()) {
            throw new PackageNotFoundException();
        }

        AmazonPackage result = null;

        for (AmazonPackage amazonPackage : packages) {
            if (asin.equals(amazonPackage.getAsin())) {
                result = amazonPackage;
                break;
            }
        }
        if (result == null) {
            throw new PackageNotFoundException();
        }

        return result;
    }

    /**
     * Does a binary search for a package in the known list of packages
     * Note: You should assume that the package list is already sorted when this method is called.
     * @param asin - The ASIN being searched for.
     * @return the Amazon Package with the target ASIN
     */
    public AmazonPackage findPackageBinary(String asin) throws PackageNotFoundException {

        if (packages.isEmpty()) {
            throw new PackageNotFoundException();
        }

        int left = 0;
        int right = packages.size() - 1;

        AmazonPackage result = null;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            AmazonPackage midPackage = packages.get(mid);

            if (asin.equals(midPackage.getAsin())) {
                result = midPackage;
                return result;

            } else if (asin.compareTo(midPackage.getAsin()) < 0 ) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        throw new PackageNotFoundException();
    }
}
