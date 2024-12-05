import React, { useEffect, useState } from "react";
import { Button, Form, InputGroup } from "react-bootstrap";
import axiosInstance from "../../api/https";
import axios from "axios";

const AddProductForm = () => {
  const [product, setProduct] = useState({
    title: "",
    inventory: "",
    price: "",
    discount: "",
    image: "",
    categories: [],
    attributes: {},
  });

  const [categories, setCategories] = useState([]);
  const [attributes, setAttributes] = useState([]);
  const [errors, setErrors] = useState({});

  const createProduct = async (productData) => {
    try {
      const formData = new FormData();

      // Append basic product details
      for (const key in productData) {
        if (Array.isArray(productData[key])) {
          // If the value is an array, append it as repeated values
          productData[key].forEach((value, index) => {
            formData.append(`${key}[${index}]`, value);
          });
        } else {
          // Otherwise, append the key-value pair normally
          formData.append(key, productData[key]);
        }
      }

      // If there's a file (image), append it as well
      if (productData.image && productData.image[0]) {
        formData.append("image", productData.image[0]);
      }

      // Make the API call
      const response = await axios.post(
        "http://localhost:8080/api/product",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data", // Important for file uploads
          },
        }
      );
      console.log(formData);
    } catch (error) {
      console.error("Error creating product:", error);
      throw error;
    }
  };

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axiosInstance.get("category/list");
        setCategories(response.data);
      } catch (error) {
        console.error("Error fetching categories:", error);
      }
    };
    const fetchAtt = async () => {
      try {
        const response = await axiosInstance.get("att/list");
        setAttributes(response.data);
      } catch (error) {
        console.error("Error fetching categories:", error);
      }
    };

    fetchCategories();
    fetchAtt();
  }, []);
  //   console.log(product);
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setErrors((prevErrors) => ({
      ...prevErrors,
      [name]: "",
    }));
    setProduct({ ...product, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const newErrors = {};

    if (!product.title) {
      newErrors.title = "Title is required";
    }

    if (!product.inventory || product.inventory <= 0) {
      newErrors.inventory = "Inventory must be a positive number";
    }

    if (!product.price || product.price <= 0) {
      newErrors.price = "Price must be a positive number";
    }

    if (!product.image) {
      newErrors.image = "Image is required";
    }

    if (!product.categories || product.categories.length === 0) {
      newErrors.categories = "At least one category must be selected";
    }

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
    } else {
      createProduct(product);
    }
    console.log("Errors:", newErrors);
  };

  const handleCategoryChange = (e) => {
    const categoryId = parseInt(e.target.value);
    const updatedCategories = e.target.checked
      ? [...product.categories, categoryId]
      : product.categories.filter((id) => id !== categoryId);

    setProduct({ ...product, categories: updatedCategories });
  };

  const renderCategories = (categories) => {
    return categories.map((category) => (
      <div
        key={category.id}
        style={{ marginLeft: `${category.parentId === 0 ? 0 : 20}px` }}
      >
        <Form.Check
          type="checkbox"
          label={category.name}
          value={category.id}
          checked={product.categories.includes(category.id)}
          onChange={handleCategoryChange}
        />
        {/* Render subcategories recursively */}
        {category.subcategories &&
          category.subcategories.length > 0 &&
          renderCategories(category.subcategories)}
      </div>
    ));
  };
  const handleAttributeChange = (e, attributeId) => {
    const { name, checked } = e.target;
    setProduct((prevProduct) => {
      const updatedAttributes = { ...prevProduct.attributes };
      if (!updatedAttributes[attributeId]) {
        updatedAttributes[attributeId] = [];
      }

      if (checked) {
        updatedAttributes[attributeId].push(name);
      } else {
        updatedAttributes[attributeId] = updatedAttributes[attributeId].filter(
          (value) => value !== name
        );
      }

      return { ...prevProduct, attributes: updatedAttributes };
    });
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setProduct({ ...product, image: file });
  };

  return (
    <div className="container mt-5">
      <h3>Add Product</h3>
      <Form onSubmit={handleSubmit}>
        {/* Title */}
        <Form.Group controlId="title" className="mb-3">
          <Form.Label>Title</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter product title"
            name="title"
            value={product.title}
            onChange={handleInputChange}
            isInvalid={!!errors.title}
          />
          {errors.title && (
            <Form.Control.Feedback type="invalid">
              {errors.title}
            </Form.Control.Feedback>
          )}
        </Form.Group>

        <div className="container">
          <div className="row">
            <div className="col-md-4">
              {/* Inventory */}
              <Form.Group controlId="inventory" className="mb-3">
                <Form.Label>Inventory</Form.Label>
                <Form.Control
                  type="number"
                  placeholder="Enter inventory quantity"
                  name="inventory"
                  value={product.inventory}
                  onChange={handleInputChange}
                  isInvalid={!!errors.inventory}
                />
              </Form.Group>
              {errors.inventory && (
                <Form.Control.Feedback type="invalid">
                  {errors.inventory}
                </Form.Control.Feedback>
              )}
            </div>

            <div className="col-md-4">
              {/* Price */}
              <Form.Group controlId="price" className="mb-3">
                <Form.Label>Price</Form.Label>
                <Form.Control
                  type="number"
                  step="0.01"
                  placeholder="Enter price"
                  name="price"
                  value={product.price}
                  onChange={handleInputChange}
                  isInvalid={!!errors.price}
                />
              </Form.Group>
              {errors.price && (
                <Form.Control.Feedback type="invalid">
                  {errors.price}
                </Form.Control.Feedback>
              )}
            </div>
            <div className="col-md-4">
              {" "}
              {/* Discount */}
              <Form.Group controlId="discount" className="mb-3">
                <Form.Label>Discount</Form.Label>
                <Form.Control
                  type="number"
                  step="0.01"
                  placeholder="Enter discount (optional)"
                  name="discount"
                  value={product.discount}
                  onChange={handleInputChange}
                  //   isInvalid={!!errors.discount}
                />
              </Form.Group>
              {/* {errors.discount && (
                <Form.Control.Feedback type="invalid">
                  {errors.discount}
                </Form.Control.Feedback>
              )} */}
            </div>
          </div>
        </div>

        {/* Image */}
        <Form.Group controlId="image" className="mb-3">
          <Form.Label>Upload Image</Form.Label>
          <Form.Control type="file" name="image" onChange={handleFileChange} />
        </Form.Group>

        <div className="container">
          <div className="row">
            {/* Categories Section */}
            <div className="col-md-6">
              <Form.Group className="mb-3">
                <Form.Label>Categories</Form.Label>
                {renderCategories(categories)}
              </Form.Group>
              {errors.categories && (
                <div className="text-danger">{errors.categories}</div>
              )}
            </div>

            {/* Attributes Section */}
            <div className="col-md-6">
              <Form.Group className="mb-3">
                <Form.Label>Attributes</Form.Label>
                {attributes.map((attribute) => (
                  <div key={attribute.id}>
                    <h5>{attribute.name}</h5>
                    {attribute.attributeValues.map((value) => (
                      <Form.Check
                        key={value.id}
                        type="checkbox"
                        label={value.name}
                        name={value.name}
                        checked={
                          product.attributes[attribute.id]?.includes(
                            value.name
                          ) || false
                        }
                        onChange={(e) => handleAttributeChange(e, attribute.id)}
                      />
                    ))}
                  </div>
                ))}
              </Form.Group>
            </div>
          </div>
        </div>

        {/* Submit Button */}
        <Button variant="primary" type="submit" onSubmit={handleSubmit}>
          Add Product
        </Button>
      </Form>
    </div>
  );
};

export default AddProductForm;
