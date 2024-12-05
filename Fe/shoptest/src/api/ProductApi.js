import axiosInstance from "./https";

export const fetchProducts = async () => {
  try {
    const response = await axiosInstance.get("product/list");
    return response.data;
  } catch (err) {
    throw new Error("Error fetching products");
  }
};

export const getCart = async (id, status) => {
  const response = await axiosInstance.get("order", {
    params: { userId: id, status },
  });
  return response.data;
};

// export const getCategory = async () => {
//   const response = await axiosInstance.get("category/list");
//   return response.data;
// };
export const addCart = async (userId, items) => {
  try {
    const response = await axiosInstance.post(`/order?userId=${userId}`, {
      status: "IN_CART",
      subTotal: "",
      shipping: "",
      total: "",
      discount: "",
      grandTotal: "",
      mobile: "",
      address: "",
      content: "",
      items: items.map((item) => ({
        productId: item.productId,
        quantity: item.quantity,
      })),
    });

    return response.data;
  } catch (error) {
    console.error("Error add to cart:", error);
    throw error;
  }
};

export const deleteInCart = async (orderId) => {
  const response = await axiosInstance.delete("item", {
    params: { orderId: orderId },
  });
  return response.data;
};

export const getProductById = async (id) => {
  const response = await axiosInstance.get("product", {
    params: { id: id },
  });
  return response.data;
};

export const updateOrder = async (
  userId,
  status,
  items,
  subTotal,
  shipping,
  total,
  discount,
  grandTotal,
  mobile,
  address,
  content
) => {
  try {
    const response = await axiosInstance.put(`/order?userId=${userId}`, {
      status,
      subTotal,
      shipping,
      total,
      discount,
      grandTotal,
      mobile,
      address,
      content,
      items: items.map((item) => ({
        orderItemId: item.orderItemId,
        productId: item.productId,
        quantity: item.quantity,
      })),
    });

    return response.data; // Return response data
  } catch (error) {
    console.error("Error updating order:", error); // Log any error
    throw error; // Re-throw error for further handling
  }
};

export const createHdn = async (supplier, totalAmount, hdnDetail, note) => {
  try {
    const response = await axiosInstance.post(`/hdn`, {
      supplier,
      totalAmount,
      note,
      hdnDetail: hdnDetail.map((item) => ({
        productId: item.productId,
        quantity: item.quantity,
        unitPrice: item.unitPrice,
        totalPrice: item.totalPrice,
      })),
    });

    return response.data;
  } catch (error) {
    console.error("Error updating order:", error);
    throw error;
  }
};

export const updateOrderStatus = async (id, status) => {
  const response = await axiosInstance.get("order", {
    params: { userId: id, status },
  });
  return response.data;
};
