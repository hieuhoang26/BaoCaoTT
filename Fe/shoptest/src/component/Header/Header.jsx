import React from "react";
import { Navbar, Container, Nav, Button } from "react-bootstrap";
import { FaShoppingCart } from "react-icons/fa";

const Header = () => {
  return (
    <Navbar bg="light" expand="lg" className="shadow-sm">
      <Container>
        {/* Brand Logo */}
        <Navbar.Brand href="/">Shop</Navbar.Brand>

        {/* Navbar Toggler for responsive behavior */}
        <Navbar.Toggle aria-controls="navbarSupportedContent" />

        {/* Navbar Links */}
        <Navbar.Collapse id="navbarSupportedContent">
          <Nav className="me-auto mb-2 mb-lg-0 ms-lg-4">
            <Nav.Link href="/" className="active">
              Home
            </Nav.Link>
            <Nav.Link href="/">About</Nav.Link>
          </Nav>

          {/* Cart Button */}
          <form className="d-flex">
            <Button variant="outline-dark" type="submit">
              <Nav.Link href="cart">
                <FaShoppingCart className="me-1" size={24} />
                Cart
                <span className="badge bg-dark text-white ms-1 rounded-pill">
                  0
                </span>
              </Nav.Link>
            </Button>
          </form>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default Header;
