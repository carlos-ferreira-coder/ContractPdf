import { z } from "zod";

const signerSchema = z.object({
  name: z
    .string()
    .trim()
    .min(3, "O nome deve ter pelo menos 3 caracteres")
    .max(150, "O nome deve ter no máximo 150 caracteres")
    .regex(/^\p{L}+\s+\p{L}+(\s*\p{L}*)*$/u, {
      message: "O nome deve conter pelo menos nome e sobrenome",
    }),

  cpfCnpj: z.string().regex(/^\d{11}$|^\d{14}$/, "Digite somente os numeros"),
  /*
  cpfCnpj: z
    .string()
    .regex(
      /^(\d{3}\.\d{3}\.\d{3}-\d{2}|\d{11}|\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}|\d{14})$/,
      "Digite no formato padrão"
    ),
  */

  email: z.email("Email inválido"),
});

const contractSchema = z.object({
  amount: z
    .number("O valor deve ser numérico")
    .positive("O valor deve ser maior que zero"),

  startDate: z.string().refine((val) => !isNaN(Date.parse(val)), {
    message: "Data inválida",
  }),

  duration: z
    .number("A duração deve ser numérica")
    .int("A duração deve ser um número inteiro")
    .positive("A duração deve ser maior que zero"),

  cityUf: z
    .string()
    .min(2, "A cidade/uf deve ter pelo menos 2 caracteres")
    .max(100, "A cidade/uf deve ter no máximo 100 caracteres"),

  description: z
    .string()
    .min(10, "A descrição deve ter no mínimo 10 caracteres")
    .max(1000, "A descrição deve ter no máximo 1000 caracteres"),
});

export const contractCreateSchema = z.object({
  contractor: signerSchema,

  contractee: signerSchema,

  contract: contractSchema,
});
