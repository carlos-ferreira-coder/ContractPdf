"use client";

import { ContractForm } from "@/components/contractForm";
import { styled } from "styled-components";
import styles from "./page.module.css";

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
      </main>
    </div>
  );
}
