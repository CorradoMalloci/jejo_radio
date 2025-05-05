import 'package:flutter/material.dart';

class RadioSearchBar extends StatelessWidget {
  final TextEditingController controller;
  final Function(String) onSearch;
  final VoidCallback onClear;

  const RadioSearchBar({
    super.key,
    required this.controller,
    required this.onSearch,
    required this.onClear,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Container(
        decoration: BoxDecoration(
          color: Theme.of(context).colorScheme.surface,
          borderRadius: BorderRadius.circular(12),
          boxShadow: [
            BoxShadow(
              color: Colors.black.withOpacity(0.05),
              blurRadius: 4,
              offset: const Offset(0, 2),
            ),
          ],
        ),
        child: TextField(
          controller: controller,
          decoration: InputDecoration(
            hintText: 'Cerca stazioni radio...',
            prefixIcon: const Icon(Icons.search),
            suffixIcon: IconButton(
              icon: const Icon(Icons.clear),
              onPressed: () {
                controller.clear();
                onClear();
              },
            ),
            border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(12),
              borderSide: BorderSide.none,
            ),
            filled: true,
            fillColor: Theme.of(context).colorScheme.surface,
          ),
          onChanged: onSearch,
          textInputAction: TextInputAction.search,
          onSubmitted: onSearch,
        ),
      ),
    );
  }
}
