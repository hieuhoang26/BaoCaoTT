import React, { useEffect, useState } from "react";
import { createHdn, fetchProducts } from "../../api/ProductApi";

const Invoice = () => {
  // Sample data for the invoice
  const [invoiceData, setInvoiceData] = useState({
    supplier: "",
    totalAmount: 0,
    note: "",
    items: [],
  });

  // getAll product
  const [product, setProduct] = useState([]);
  const [newProduct, setNewProduct] = useState({
    product: "",
    // productId: "",
    quantity: 1,
    unitPrice: 0,
    totalPrice: 0,
  });

  useEffect(() => {
    const getProducts = async () => {
      const data = await fetchProducts();
      setProduct(data);
    };
    getProducts();
  }, []);

  const handleProductChange = (e) => {
    const selectedProduct = product.find(
      (prod) => prod.title === e.target.value
    );
    setNewProduct({
      ...newProduct,
      product: e.target.value,
      productId: selectedProduct ? selectedProduct.id : "",
      // unitPrice: selectedProduct ? selectedProduct.price : 0,
    });
  };
  // console.log(newProduct);
  const handleCreateInvoice = async () => {
    const { supplier, note, items, totalAmount } = invoiceData;

    try {
      const response = await createHdn(supplier, totalAmount, items, note);
      console.log("Invoice created successfully:", response);
      setInvoiceData({ items: [], totalAmount: 0, supplier: "", note: "" });
    } catch (error) {
      console.error("Failed to create invoice:", error);
    }
  };

  const handleQuantityChange = (e) => {
    const quantity = e.target.value;
    const totalPrice = quantity * newProduct.unitPrice;
    setNewProduct({
      ...newProduct,
      quantity: quantity,
      totalPrice: totalPrice,
    });
  };

  const handleUnitPriceChange = (e) => {
    const unitPrice = e.target.value;
    const totalPrice = newProduct.quantity * unitPrice;
    setNewProduct({
      ...newProduct,
      unitPrice: unitPrice,
      totalPrice: totalPrice,
    });
  };

  const addProductToInvoice = () => {
    const updatedItems = [
      ...invoiceData.items,
      {
        productId: newProduct.productId,
        product: newProduct.product,
        quantity: newProduct.quantity,
        unitPrice: newProduct.unitPrice,
        totalPrice: newProduct.totalPrice,
      },
    ];

    const updatedInvoice = {
      ...invoiceData,
      items: updatedItems,
      totalAmount: updatedItems.reduce((acc, item) => acc + item.totalPrice, 0),
    };

    setInvoiceData(updatedInvoice);
    setNewProduct({ product: "", quantity: 1, unitPrice: 0, totalPrice: 0 });
  };
  console.log(invoiceData);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setInvoiceData({
      ...invoiceData,
      [name]: value,
    });
  };

  return (
    <div className="container mt-5">
      <div className="card">
        <div className="card-header">
          <h4>Purchase Invoice</h4>
        </div>
        <div className="card-body">
          <div className="row mb-3">
            <div className="col-md-6">
              <div className="mb-3">
                <label htmlFor="supplier">Supplier:</label>
                <input
                  type="text"
                  id="supplier"
                  className="form-control"
                  name="supplier"
                  value={invoiceData.supplier}
                  onChange={handleInputChange}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="note">Note:</label>
                <textarea
                  id="note"
                  className="form-control"
                  name="note"
                  rows="3"
                  value={invoiceData.note}
                  onChange={handleInputChange}
                ></textarea>
              </div>
            </div>
            <div className="col-md-6 text-md-right">
              <p>
                <strong>Total Amount:</strong> $
                {invoiceData.totalAmount.toFixed(2)}
              </p>
            </div>
          </div>

          {/* Add Product Form */}
          <div className="mb-3">
            <label htmlFor="productSelect">Choose Product:</label>
            <select
              id="productSelect"
              className="form-control"
              value={newProduct.product}
              onChange={handleProductChange}
            >
              <option value="">Select Product</option>
              {product.map((prod) => (
                <option key={prod.id} value={prod.title}>
                  {prod.title}
                </option>
              ))}
            </select>
          </div>

          <div className="mb-3 row">
            <div className="col-md-4">
              <label htmlFor="quantityInput">Quantity:</label>
              <input
                id="quantityInput"
                type="number"
                className="form-control"
                value={newProduct.quantity}
                onChange={handleQuantityChange}
                min="1"
              />
            </div>
            <div className="col-md-4">
              <label htmlFor="unitPriceInput">Unit Price:</label>
              <input
                id="unitPriceInput"
                type="number"
                className="form-control"
                value={newProduct.unitPrice}
                onChange={handleUnitPriceChange}
                min="0"
                step="1"
              />
            </div>
            <div className="col-md-4">
              <label htmlFor="totalPriceInput">Total Price:</label>
              <input
                id="totalPriceInput"
                type="number"
                className="form-control"
                value={newProduct.totalPrice}
                readOnly
              />
            </div>
          </div>

          <button
            className="btn btn-primary"
            onClick={addProductToInvoice}
            disabled={!newProduct.product || newProduct.quantity <= 0}
          >
            Add Product
          </button>

          {/* Product Table */}
          <table className="table table-bordered mt-4">
            <thead>
              <tr>
                <th>Product</th>
                <th>Quantity</th>
                <th>Unit Price</th>
                <th>Total Price</th>
              </tr>
            </thead>
            <tbody>
              {invoiceData.items.map((item, index) => (
                <tr key={index}>
                  <td>{item.product}</td>
                  <td>{item.quantity}</td>
                  <td>${item.unitPrice}</td>
                  <td>${item.totalPrice.toFixed(2)}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="d-flex justify-content-end">
            <button
              className="btn btn-primary"
              onClick={handleCreateInvoice}
              disabled={invoiceData.items.length === 0}
            >
              Submit
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Invoice;
