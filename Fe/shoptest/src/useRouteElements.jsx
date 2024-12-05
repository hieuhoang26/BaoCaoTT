import { useRoutes } from "react-router-dom";
import MainLayout from "./component/MainLayout/MainLayout";
import Cart from "./component/Page/Cart";
import ProductSection from "./component/Page/ProductSection";
import Invoice from "./component/Page/ProductForm";
import AddProductForm from "./component/Page/AddProductForm";

export default function useRouteElements() {
  const routeElements = useRoutes([
    {
      path: "/",
      index: true,
      element: (
        <MainLayout>
          <ProductSection />
        </MainLayout>
      ),
    },
    {
      path: "/cart",
      element: (
        <MainLayout>
          <Cart />
        </MainLayout>
      ),
    },
    {
      path: "/hoadon",
      element: (
        <MainLayout>
          <Invoice />
        </MainLayout>
      ),
    },
    {
      path: "/create",
      element: (
        <MainLayout>
          <AddProductForm />
        </MainLayout>
      ),
    },
  ]);
  return routeElements;
}
