"use client";

import { ContractForm } from "@/components/contractForm";
import { styled } from "styled-components";
import styles from "./page.module.css";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";

const DivContainer = styled.div`
  display: "flex";
  flex-direction: column;
  align-items: center;
  margin: 0 auto;
  gap: 20px;
  width: 60%;
  max-width: 800px;
`;

export default function Home() {
  return (
    <div className={styles.page}>
      <main className={styles.main}>
        <DivContainer>
          <ContractForm />
        </DivContainer>
        <ToastContainer
          position="top-right"
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
        />
      </main>
    </div>
  );
}
