import React, { useEffect, useState } from "react";
import {
  deleteInCart,
  getCart,
  getProductById,
  updateOrder,
  updateOrderStatus,
} from "../../api/ProductApi";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [mobile, setMobile] = useState("");
  const [address, setAddress] = useState("");
  const [orderSummary, setOrderSummary] = useState({
    subTotal: 0,
    shipping: 0,
    total: 0,
    discount: 0,
    grandTotal: 0,
  });

  // Function to calculate totals
  const calculateTotals = (items) => {
    const subTotal = items.reduce(
      (total, item) => total + item.price * item.quantity,
      0
    );

    const shipping = 10;
    const discount = items.reduce(
      (totalDiscount, item) =>
        totalDiscount + item.price * item.quantity * (item.discount / 100 || 0),
      0
    );
    const total = subTotal + shipping - discount;
    const grandTotal = total;

    setOrderSummary({ subTotal, shipping, total, discount, grandTotal });
  };
  const userId = 1;
  const status = "IN_CART";
  const status2 = "SUCCESS";
  useEffect(() => {
    const fetchCartData = async () => {
      try {
        const cartData = await getCart(userId, status);
        console.log(cartData);
        const itemsWithDetails = await Promise.all(
          cartData.items.map(async (item) => {
            const productDetails = await getProductById(item.productId);

            return {
              ...productDetails,
              quantity: item.quantity,
              productId: item.productId,
              orderItemId: item.orderItemId,
            };
          })
        );
        console.log(itemsWithDetails);

        setCartItems(itemsWithDetails);
        calculateTotals(itemsWithDetails);
      } catch (error) {
        console.error("Error fetching cart data:", error);
      }
    };

    fetchCartData();
  }, []);
  const handleUpdateStatus = async () => {
    try {
      const response = await updateOrderStatus(userId, status2);
      console.log("Update successfully:", response);
    } catch (error) {
      console.error("Failed:", error);
    }
  };

  const updateOrderOnServer = async (updatedItems) => {
    try {
      await updateOrder(
        userId,
        status,
        updatedItems,
        orderSummary.subTotal,
        orderSummary.shipping,
        orderSummary.total,
        orderSummary.discount,
        orderSummary.grandTotal,
        mobile,
        address,
        "Cart updated"
      );
    } catch (error) {
      console.error("Error updating order:", error);
    }
  };

  const handleRemoveItem = async (productId, orderItemId) => {
    const updatedItems = cartItems.filter(
      (item) => item.productId !== productId
    );
    setCartItems(updatedItems);
    calculateTotals(updatedItems);
    // updateOrderOnServer(updatedItems);
    try {
      const response = await deleteInCart(orderItemId);
    } catch (error) {
      console.error("Error adding to cart:", error);
      alert("Có lỗi xảy ra khi thêm sản phẩm vào giỏ hàng");
    }
  };

  const handleIncreaseQuantity = (productId) => {
    const updatedItems = cartItems.map((item) =>
      item.productId === productId
        ? { ...item, quantity: item.quantity + 1 }
        : item
    );
    setCartItems(updatedItems);
    calculateTotals(updatedItems);
    updateOrderOnServer(updatedItems);
  };

  // Handle quantity decrease
  const handleDecreaseQuantity = (productId) => {
    const updatedItems = cartItems.map((item) =>
      item.productId === productId && item.quantity > 1
        ? { ...item, quantity: item.quantity - 1 }
        : item
    );
    setCartItems(updatedItems);
    calculateTotals(updatedItems);
    updateOrderOnServer(updatedItems);
  };

  // Handle quantity input change
  const handleQuantityChange = (productId, value) => {
    const newQuantity = Math.max(1, parseInt(value) || 1); // Ensure a minimum value of 1
    const updatedItems = cartItems.map((item) =>
      item.productId === productId ? { ...item, quantity: newQuantity } : item
    );
    setCartItems(updatedItems);
    calculateTotals(updatedItems);
    updateOrderOnServer(updatedItems);
  };
  //   const handleMobileChange = (event) => setMobile(event.target.value);
  //   const handleAddressChange = (event) => setAddress(event.target.value);

  return (
    <div className="cart">
      <section className="py-5">
        <div className="container px-4 px-lg-5 my-5">
          <div className="row">
            <div className="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
              <div className="table-responsive">
                <table className="table">
                  <thead>
                    <tr>
                      <th scope="col" className="border-0 bg-light">
                        <div className="p-2 px-3 text-uppercase">Product</div>
                      </th>
                      <th scope="col" className="border-0 bg-light">
                        <div className="py-2 text-uppercase">Price</div>
                      </th>
                      <th scope="col" className="border-0 bg-light">
                        <div className="py-2 text-uppercase">Discount</div>
                      </th>
                      <th scope="col" className="border-0 bg-light">
                        <div className="py-2 text-uppercase">Quantity</div>
                      </th>
                      <th scope="col" className="border-0 bg-light">
                        <div className="py-1 text-uppercase"></div>
                      </th>
                    </tr>
                  </thead>
                  <tbody>
                    {cartItems.map((item) => (
                      <tr key={item.productId}>
                        <th scope="row" className="border-0">
                          <div className="p-2">
                            <img
                              src={
                                // item.image || "https://via.placeholder.com/70"
                                item.image
                                  ? `http://localhost:8080/images/${item.image}`
                                  : "https://dummyimage.com/450x300/dee2e6/6c757d.jpg"
                              }
                              alt={item.name}
                              width="70"
                              className="img-fluid rounded shadow-sm"
                            />
                            <div className="ms-3 d-inline-block align-middle">
                              <h5 className="mb-0">
                                <a
                                  href="#"
                                  className="text-dark d-inline-block align-middle"
                                >
                                  {item.name}
                                </a>
                              </h5>
                            </div>
                          </div>
                        </th>
                        <td className="border-0 align-middle">
                          <strong>${item.price.toFixed(2)}</strong>
                        </td>
                        <td className="border-0 align-middle">
                          <strong>${item.discount.toFixed(2)}</strong>
                        </td>
                        <td className="border-0 align-middle">
                          <td className="border-0 align-middle">
                            <div className="d-flex align-items-center">
                              <button
                                className="btn btn-sm btn-outline-secondary"
                                onClick={() =>
                                  handleDecreaseQuantity(item.productId)
                                }
                                disabled={item.quantity <= 1}
                              >
                                -
                              </button>

                              <input
                                type="text"
                                value={item.quantity}
                                min="1"
                                onChange={(e) =>
                                  handleQuantityChange(
                                    item.productId,
                                    e.target.value
                                  )
                                }
                                className="form-control mx-2 text-center"
                                style={{ width: "60px" }}
                              />

                              {/* Increase Quantity Button */}
                              <button
                                className="btn btn-sm btn-outline-secondary"
                                onClick={() =>
                                  handleIncreaseQuantity(item.productId)
                                }
                              >
                                +
                              </button>
                            </div>
                          </td>
                        </td>
                        <td className="border-0 align-middle">
                          <a
                            href="#"
                            className="text-dark"
                            onClick={() =>
                              handleRemoveItem(item.productId, item.orderItemId)
                            }
                          >
                            {/* <i className="bi bi-trash-fill"></i> */}
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              width="16"
                              height="16"
                              fill="currentColor"
                              className="bi bi-trash"
                              viewBox="0 0 16 16"
                            >
                              <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z" />
                              <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z" />
                            </svg>
                          </a>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>

          <div className="row py-5 p-4 bg-white rounded shadow-sm">
            <div className="col-lg-6">
              <div className="bg-light rounded-pill px-4 py-3 text-uppercase fw-bold">
                Update Product Details
              </div>
              <div className="p-4">
                <p className="mb-4">
                  <em>
                    Enter mobile and address to update the product details.
                  </em>
                </p>

                <div className="input-group mb-4 border rounded-pill p-2">
                  <input
                    type="text"
                    placeholder="Enter mobile"
                    className="form-control border-0"
                    value={mobile}
                    onChange={(e) => setMobile(e.target.value)} // Update state
                    required
                  />
                </div>
                {/* Address input field */}
                <div className="input-group mb-4 border rounded-pill p-2">
                  <input
                    type="text"
                    placeholder="Enter address"
                    className="form-control border-0"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)} // Update state
                    required
                  />
                </div>
              </div>
            </div>

            <div className="col-lg-6">
              <div className="bg-light rounded-pill px-4 py-3 text-uppercase fw-bold">
                Order summary
              </div>
              <div className="p-4">
                <ul className="list-unstyled mb-4">
                  <li className="d-flex justify-content-between py-3 border-bottom">
                    <strong className="text-muted">Order Subtotal</strong>
                    <strong>${orderSummary.subTotal.toFixed(2)}</strong>
                  </li>
                  <li className="d-flex justify-content-between py-3 border-bottom">
                    <strong className="text-muted">
                      Shipping and handling
                    </strong>
                    <strong>${orderSummary.shipping.toFixed(2)}</strong>
                  </li>
                  <li className="d-flex justify-content-between py-3 border-bottom">
                    <strong className="text-muted">Discount</strong>
                    <strong>${orderSummary.discount.toFixed(2)}</strong>
                  </li>
                  <li className="d-flex justify-content-between py-3 border-bottom">
                    <strong className="text-muted">Total</strong>
                    <h5 className="fw-bold">
                      ${orderSummary.total.toFixed(2)}
                    </h5>
                  </li>
                  <li className="d-flex justify-content-between py-3 border-bottom">
                    <strong className="text-muted">Grand Total</strong>
                    <h5 className="fw-bold">
                      ${orderSummary.grandTotal.toFixed(2)}
                    </h5>
                  </li>
                </ul>
                {/* <a
                  href="#"
                  className="btn btn-dark rounded-pill py-2 d-md-block"
                >
                  Proceed to checkout
                </a> */}
                <button
                  className="btn btn-dark rounded-pill py-2 d-md-block"
                  onClick={handleUpdateStatus}
                >
                  Proceed to checkout
                </button>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Cart;
