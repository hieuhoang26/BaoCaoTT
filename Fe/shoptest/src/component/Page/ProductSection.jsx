import { useEffect, useState } from "react";

import { Card, Button, Row, Col } from "react-bootstrap";
import { addCart, fetchProducts } from "../../api/ProductApi";

const ProductSection = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const getProducts = async () => {
      try {
        const data = await fetchProducts();
        setProducts(data);
      } catch (err) {
        setError("Error fetching products");
      } finally {
        setLoading(false);
      }
    };

    getProducts();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  const userId = 1;

  const handleAddToCart = async (productId, quantity) => {
    try {
      const response = await addCart(userId, [{ productId, quantity }]);
      alert("Sản phẩm đã được thêm vào giỏ hàng");
    } catch (error) {
      console.error("Error adding to cart:", error);
      alert("Có lỗi xảy ra khi thêm sản phẩm vào giỏ hàng");
    }
  };

  return (
    <section className="py-5">
      <div className="container px-4 px-lg-5 mt-5">
        <Row className="gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
          {products.map((product) => (
            <Col key={product.id} className="mb-5">
              <Card className="h-100">
                <Card.Img
                  variant="top"
                  src={
                    product.image
                      ? `http://localhost:8080/images/${product.image}`
                      : "https://dummyimage.com/450x300/dee2e6/6c757d.jpg"
                  }
                  alt={product.title}
                />
                <Card.Body className="p-4">
                  <div className="text-center">
                    <Card.Title className="fw-bolder">
                      {product.title}
                    </Card.Title>
                    {product.discount ? (
                      <>
                        <span className="text-muted text-decoration-line-through">
                          ${product.price}
                        </span>{" "}
                        $
                        {product.price -
                          (product.price * product.discount) / 100}
                      </>
                    ) : (
                      <span>${product.price}</span>
                    )}
                  </div>
                </Card.Body>
                <Card.Footer className="p-4 pt-0 border-top-0 bg-transparent">
                  <div className="text-center">
                    <Button variant=" mt-auto me-2" className="btn-danger">
                      Buy
                    </Button>
                    <Button
                      variant=" mt-auto "
                      className="btn-warning"
                      onClick={() => handleAddToCart(product.id, 1)}
                    >
                      Add to Cart
                    </Button>
                  </div>
                </Card.Footer>
              </Card>
            </Col>
          ))}
        </Row>
      </div>
    </section>
  );
};

export default ProductSection;
